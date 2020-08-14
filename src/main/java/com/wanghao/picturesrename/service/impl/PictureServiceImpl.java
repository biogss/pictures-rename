package com.wanghao.picturesrename.service.impl;

import com.wanghao.picturesrename.config.PicConfig;
import com.wanghao.picturesrename.dao.PicDao;
import com.wanghao.picturesrename.dao.UserDao;
import com.wanghao.picturesrename.entity.Picture;
import com.wanghao.picturesrename.entity.User;
import com.wanghao.picturesrename.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片服务类实现类
 * @author wanghao
 */
@Service
public class PictureServiceImpl implements PictureService {

	private final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

	@Autowired
	private PicDao picDao;

	@Autowired
	PicConfig picConfig;

	@Override
	public List<Picture> getPictures(User user) {
		List<Picture> pictureList = new ArrayList<>();
		logger.info("UserInfo:" + user.toString());
		//大头照
		List<Picture> facePhotoList = picDao.getPictures(user.getUserId(), "2");
		if (facePhotoList != null && facePhotoList.size() > 0) {
			pictureList.add(facePhotoList.get(0));
		}else {
			logger.warn("用户[" + user.getUserName() + "|" + user.getIdNo() + "]大头照不存在！");
		}
		//身份证正面照
		List<Picture> idCardPhotoList = picDao.getPictures(user.getUserId(), "3");
		if (idCardPhotoList != null && idCardPhotoList.size() > 0) {
			pictureList.add(idCardPhotoList.get(0));
		}else {
			logger.warn("用户[" + user.getUserName() + "|" + user.getIdNo() + "]身份证正面照不存在！");
		}
		//获取公安照
		List<Picture> policePhotoList = picDao.getPolicePhoto(user.getIdNo());
		if (policePhotoList != null && policePhotoList.size() > 0) {
			pictureList.add(policePhotoList.get(0));
		}else {
			logger.warn("用户[" + user.getUserName() + "|" + user.getIdNo() + "]公安照不存在！");
		}
		if ("read-file".equals(picConfig.getBusiness())) {
			List<Picture> idCardBackPhotoList = picDao.getPictures(user.getUserId(), "4");
			if (idCardBackPhotoList != null && idCardBackPhotoList.size() > 0) {
				pictureList.add(idCardBackPhotoList.get(0));
			}else {
				logger.warn("用户[" + user.getUserName() + "|" + user.getIdNo() + "]身份证反面照不存在！");
			}
		}
		return pictureList;
	}

	@Override
	public List<Picture> getPolicePhoto(String idNo) {
		return picDao.getPolicePhoto(idNo);
	}
}
