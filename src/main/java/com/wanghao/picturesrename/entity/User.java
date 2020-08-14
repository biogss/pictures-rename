package com.wanghao.picturesrename.entity;

import org.springframework.stereotype.Component;

/**
 * 用户信息类
 * @author wanghao
 */
@Component
public class User {

	private int userId;

	private String idNo;

	private String userName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", idNo='" + idNo + '\'' +
				", userName='" + userName + '\'' +
				'}';
	}
}
