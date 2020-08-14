package com.wanghao.picturesrename.dao;

import com.wanghao.picturesrename.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Dao
 * @author wanghao
 */
@Repository
@Mapper
public interface UserDao {

	/**
	 * 分页查询用户信息
	 * @param page 页数 从1开始计算
	 * @param pageSize 每页数据条数
	 * @return List<User>
	 */
	List<User> getUserInfo(@Param("page") int page, @Param("pageSize") int pageSize);
}
