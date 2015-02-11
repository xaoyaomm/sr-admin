package com.store.api.mongo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Staff implements Serializable{

	private static final long serialVersionUID = 6050153096658566045L;
	
	@Id
	private long id;
	
	/** 业务员姓名 **/
	private String name="";
	
	/** 业务员手机号 **/
	private String phone="";
	
	/** 创建时间 **/
	private long createDate=0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
