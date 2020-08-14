package com.wanghao.picturesrename.entity;

import org.springframework.stereotype.Component;

/**
 * @date 2020-08-05
 * @author wanghao
 */
@Component
public class Picture {

	private String url;

	private String type;

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Picture{" +
				"url='" + url + '\'' +
				", type='" + type + '\'' +
				", userId=" + userId +
				'}';
	}
}
