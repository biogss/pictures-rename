package com.wanghao.picturesrename.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "picture.name")
public class PicConfig {

	private int totalPage;

	private int pageSize;

	private String sourceFilePrefix;

	private String newFilePrefix;

	private String business;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSourceFilePrefix() {
		return sourceFilePrefix;
	}

	public void setSourceFilePrefix(String sourceFilePrefix) {
		this.sourceFilePrefix = sourceFilePrefix;
	}

	public String getNewFilePrefix() {
		return newFilePrefix;
	}

	public void setNewFilePrefix(String newFilePrefix) {
		this.newFilePrefix = newFilePrefix;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
}
