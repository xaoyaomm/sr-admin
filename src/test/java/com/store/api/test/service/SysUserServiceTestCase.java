package com.store.api.test.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.store.api.common.MD5;
import com.store.api.mongo.entity.Area;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.service.AreaService;
import com.store.api.mongo.service.SysUserService;

public class SysUserServiceTestCase extends BaseServiceTestCase {

	@Autowired
	SysUserService service;
	@Autowired
	AreaService areaService;

	@Test
	public void testSave() {
		SysUser user = new SysUser();
		user.setCreateTime(System.currentTimeMillis());
		user.setNickName("admin");
		user.setPhone("13111111111");
		user.setPwd(MD5.encrypt("12345"));
		user.setRoleId(1);
		user.setStatus(1);
		user.setUserName("admin");
		service.save(user);
	}

	@Test
	public void testArea() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("f:/Baidu_cityCode.txt"));
		String data = br.readLine();// 一次读入一行，直到读入null为文件结束
		List<Area> list = new ArrayList<Area>();
		while (data != null) {
			System.out.println(data);
			String[] arrs = data.split(",");
			System.out.println(arrs[0] + "" + arrs[1] + "" + arrs[2]);
			Area area = new Area();
			area.setId(Long.parseLong(arrs[0].trim()));
			area.setTitle(arrs[1].trim());
			area.setParentId(Long.parseLong(arrs[2].trim()));
			if (Long.valueOf(arrs[2].trim()) == 1)
				area.setType(1);
			else
				area.setType(2);
			list.add(area);
			data = br.readLine(); // 接着读下一行
		}
		areaService.save(list);
	}

}
