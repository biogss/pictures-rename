package com.wanghao.picturesrename.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

	@Bean("taskExecutor")
	public ThreadPoolExecutor getThreadPoolExecutor() {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20,
				60000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		return poolExecutor;
	}
}
