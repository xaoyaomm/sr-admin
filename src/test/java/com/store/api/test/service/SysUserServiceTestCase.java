package com.store.api.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.store.api.common.MD5;
import com.store.api.mongo.entity.SysUser;
import com.store.api.mongo.service.SysUserService;

public class SysUserServiceTestCase extends BaseServiceTestCase {
	
	@Autowired
	SysUserService service;
	
	@Test
	public void testSave(){
		SysUser user=new SysUser();
		user.setCreateTime(System.currentTimeMillis());
		user.setNickName("admin");
		user.setPhone("13111111111");
		user.setPwd(MD5.encrypt("12345"));
		user.setRoleId(1);
		user.setStatus(1);
		user.setUserName("admin");
		service.save(user);
	}

}
