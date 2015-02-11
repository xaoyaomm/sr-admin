package com.store.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.store.api.common.AjaxObject;
import com.store.api.common.PageBean;
import com.store.api.mongo.entity.Staff;
import com.store.api.mongo.service.StaffService;
import com.store.api.utils.Utils;

@Controller
@Scope("prototype")
@RequestMapping("staff")
public class StaffController extends BaseAction{
	
	@Autowired
	private StaffService staffService;

    @RequestMapping("/search")
    public ModelAndView list(PageBean pageBean,@RequestParam(value = "name", required = false, defaultValue = "")
    String name,@RequestParam(value = "phone", required = false, defaultValue = "")
    String phone) {
        List<Staff> list = staffService.findByNameLikeOrPhone(pageBean, name,phone);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("staffList", list);
        result.put("page", pageBean);
        return new ModelAndView("staff/list", result);
    }

    @RequestMapping(value = "/cre")
    public String create() {
        return "staff/add";
    }
    
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String add(Staff staff) {
        AjaxObject ajaxObject = null;
        if (null != staff) {
            Staff realStaff = staffService.findByName(staff.getName());
            if (null != realStaff) {
                ajaxObject = AjaxObject.newError("新增失败，此用户已经存在");
            } else {
            	staff.setCreateDate(System.currentTimeMillis());
            	staffService.save(staff);
                ajaxObject = AjaxObject.newOk("添加成功");
            }
        }
        return ajaxObject.toString();
    }
    
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String update(Staff staff){
        AjaxObject ajaxObject = null;
        if(null!=staff){
        	Staff realStaff=staffService.findOne(staff.getId());
            if(null!=realStaff){
                if(!Utils.isEmpty(staff.getPhone()))
                	realStaff.setPhone(staff.getPhone());
                if(!Utils.isEmpty(staff.getName()))
                	realStaff.setName(staff.getName());
                staffService.save(realStaff);
                ajaxObject = AjaxObject.newOk("修改成功");
            }else
                ajaxObject = AjaxObject.newError("修改失败");
        }else
            ajaxObject = AjaxObject.newError("修改失败");
        return ajaxObject.toString();
    }
    
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public String del(@RequestParam(value = "id", required = false, defaultValue = "0")
    long id){
        AjaxObject ajaxObject = null;
        Staff staff=staffService.findOne(id);
        if(null!=staff){
        	staffService.delete(id);
                ajaxObject = AjaxObject.newOk("删除成功");
                ajaxObject.setCallbackType("");
        }else{
            ajaxObject = AjaxObject.newError("删除失败");
            ajaxObject.setCallbackType("");
        }
        return ajaxObject.toString();
    }
    
    @RequestMapping(value = "/detail/{id}")
    public ModelAndView detail(@PathVariable long id){
        Map<String, Object> result = new HashMap<String, Object>();
        Staff staff=staffService.findOne(id);
        if(null==staff)
        	staff=new Staff();
        result.put("staff", staff);
        return new ModelAndView("staff/detail", result);
    }

}
