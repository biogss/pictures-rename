package com.wanghao.picturesrename.service;

import com.wanghao.picturesrename.entity.User;

import java.util.List;

/**
 * 用户信息服务类
 * @author wanghao
 */
public interface UserService {

	/**
	 * 获取用户信息
	 * @param page 页数
	 * @param pageSize 每页数据条数
	 * @return List<User>
	 */
	List<User> getUserList(int page, int pageSize);
}
