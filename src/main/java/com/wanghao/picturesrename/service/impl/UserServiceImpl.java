package com.wanghao.picturesrename.service.impl;

import com.wanghao.picturesrename.dao.UserDao;
import com.wanghao.picturesrename.entity.User;
import com.wanghao.picturesrename.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public List<User> getUserList(int page, int pageSize) {
		if (page < 1 || pageSize < 1) {
			throw new IllegalArgumentException("页数或者条目必须大于等于1");
		}
		return userDao.getUserInfo(page, pageSize);
	}
}
