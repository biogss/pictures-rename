package com.wanghao.picturesrename.service.impl;

import com.wanghao.picturesrename.dao.UserDao;
import com.wanghao.picturesrename.dao.VideoDao;
import com.wanghao.picturesrename.entity.User;
import com.wanghao.picturesrename.entity.Video;
import com.wanghao.picturesrename.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

	private final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

	@Autowired
	private VideoDao videoDao;

	@Override
	public List<Video> getVideoListByUserInfo(User user) {
		List<Video> videoList = new ArrayList<>();
		logger.info("UserInfo:" + user.toString());
		//视频
		List<Video> videos = videoDao.getVideoListByIdNo(user.getUserId());
		if (videos != null && videos.size() > 0) {
			videoList.add(videos.get(0));
		}else {
			logger.warn("用户[" + user.getUserName() + "|" + user.getIdNo() + "]视频不存在！");
		}
		return videoList;
	}
}
