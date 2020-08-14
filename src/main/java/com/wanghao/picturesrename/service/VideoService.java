package com.wanghao.picturesrename.service;

import com.wanghao.picturesrename.entity.User;
import com.wanghao.picturesrename.entity.Video;

import java.util.List;

public interface VideoService {

	/**
	 * 获取视频信息
	 * @param user 用户对象
	 * @return List<Video>
	 */
	List<Video> getVideoListByUserInfo(User user);
}
