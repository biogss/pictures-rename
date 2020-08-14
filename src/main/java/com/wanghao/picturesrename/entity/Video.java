package com.wanghao.picturesrename.entity;

public class Video {

	private int userId;

	private String url;

	private String type;

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
		return "Video{" +
				"userId=" + userId +
				", url='" + url + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
