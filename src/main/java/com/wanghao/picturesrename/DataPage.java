package com.wanghao.picturesrename;

import org.springframework.stereotype.Component;

@Component
public class DataPage {

	private static int  page;

	private static int pageSize;

	public static int getPage() {
		return page;
	}

	public static void setPage(int page) {
		DataPage.page = page;
	}

	public static int getPageSize() {
		return pageSize;
	}

	public static void setPageSize(int pageSize) {
		DataPage.pageSize = pageSize;
	}
}