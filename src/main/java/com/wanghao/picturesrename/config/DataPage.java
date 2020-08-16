package com.wanghao.picturesrename.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DataPage {

	private static AtomicInteger page;

	private static int pageSize;

	public DataPage() {
	    super();
	    page = new AtomicInteger(0);
	    pageSize = 0;
    }

	public static AtomicInteger getPage() {
		page.incrementAndGet();
		return page;
	}

	public static void setPage(AtomicInteger page) {
		DataPage.page = page;
	}

	public static int getPageSize() {
		return pageSize;
	}

	public static void setPageSize(int pageSize) {
		DataPage.pageSize = pageSize;
	}
}