package com.store.api.mongo.entity;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.store.api.mongo.entity.enumeration.UserType;

@Document
public class SysUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	
	/** 用户名 **/
	private String userName="";
	
	/** 昵称 **/
	private String nickName="";
	
	/** 密码 **/
	private String pwd="";
	
	/** 手机号 **/
	private String phone="";
	
	/** 状态 1有效  0无效 **/
	private int status=1;
	
	/** 帐号创建时间 **/
    private long createTime=System.currentTimeMillis();
	
	/** 最后使用时间 **/
	private long lastUserTime=System.currentTimeMillis();
	
	/** 角色ID 1:管理员 2:普通用户 **/
	private int roleId=2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastUserTime() {
		return lastUserTime;
	}

	public void setLastUserTime(long lastUserTime) {
		this.lastUserTime = lastUserTime;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
