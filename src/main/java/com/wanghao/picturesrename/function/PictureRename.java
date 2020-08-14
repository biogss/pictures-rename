package com.wanghao.picturesrename.function;

import com.wanghao.picturesrename.entity.Picture;
import com.wanghao.picturesrename.entity.User;
import com.wanghao.picturesrename.service.PictureService;
import com.wanghao.picturesrename.service.UserService;
import com.wanghao.picturesrename.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PictureRename {

	private final Logger logger = LoggerFactory.getLogger(PictureRename.class);

	@Autowired
	PictureService pictureService;

	@Autowired
	UserService userService;

	public void pictureRename(int totalPage, int pageSize, String sourceFilePrefix, String newFilePrefix) {
		StringBuilder sourceFileUrl = new StringBuilder();
		StringBuilder newFileUrl = new StringBuilder();
		for (int page = 1; page <= totalPage; page++) {
			//获取开户成功的用户信息
			List<User> userList = userService.getUserList(page, pageSize);
			if (userList != null) {
				for (User user : userList) {
					//获取用户图片信息
					List<Picture> pictureList = pictureService.getPictures(user);
					for (Picture picture : pictureList) {
						logger.info("page:" + page  + "| " + picture.toString());
						sourceFileUrl.append(sourceFilePrefix).append(picture.getUrl());
						newFileUrl.append(newFilePrefix).append(picture.getUserId()).append("-").append(picture.getType());
						//1-获取公安照png格式，其他jpg格式
						newFileUrl.append("1".equals(picture.getType()) ? ".png" : ".jpg");
						FileUtil.readAndWriterFile(sourceFileUrl.toString(), newFileUrl.toString());
						//清空builder对象
						sourceFileUrl.delete(0, sourceFileUrl.length());
						newFileUrl.delete(0, newFileUrl.length());
					}
					pictureList.clear();
				}
			}
		}
		logger.info("done！！！！！！！！！！！！！！！！！！！！！");
	}
}
