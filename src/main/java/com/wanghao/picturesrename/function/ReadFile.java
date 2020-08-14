package com.wanghao.picturesrename.function;

import com.wanghao.picturesrename.DataPage;
import com.wanghao.picturesrename.config.PicConfig;
import com.wanghao.picturesrename.entity.Picture;
import com.wanghao.picturesrename.entity.User;
import com.wanghao.picturesrename.entity.Video;
import com.wanghao.picturesrename.service.PictureService;
import com.wanghao.picturesrename.service.UserService;
import com.wanghao.picturesrename.service.VideoService;
import com.wanghao.picturesrename.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 读取用户所有信息是否存在
 */
@Component
public class ReadFile implements Runnable{

	private final Logger logger = LoggerFactory.getLogger(ReadFile.class);

	@Autowired
	VideoService videoService;

	@Autowired
	UserService userService;

	@Autowired
	PictureService pictureService;

	@Autowired
	PicConfig picConfig;

	private void logFileInfo(String sourceFilePrefix) {
		boolean fileIsExists = FileUtil.isExists(sourceFilePrefix);
		if (!fileIsExists) {
			logger.warn("文件不存在：" + sourceFilePrefix);
		}
	}

	@Override
	public void run() {
		int page = DataPage.getPage();
		int pageSize = DataPage.getPageSize();
		String sourceFilePrefix = picConfig.getSourceFilePrefix();
		StringBuilder sourceFileUrl = new StringBuilder();
		//获取开户成功的用户信息
		List<User> userList = userService.getUserList(page, pageSize);
		if (userList != null) {
			for (User user : userList) {
				//获取开户成功的用户对应的视频信息
				List<Video> videoList = videoService.getVideoListByUserInfo(user);
				for (Video video : videoList) {
					logger.info("page:" + page + "| " + video.toString());
					logFileInfo(sourceFileUrl.append(sourceFilePrefix).append(video.getUrl()).toString());
					//清空builder对象
					sourceFileUrl.delete(0, sourceFileUrl.length());
				}
				videoList.clear();
				//获取用户图片信息
				List<Picture> pictureList = pictureService.getPictures(user);
				for (Picture picture : pictureList) {
					logger.info("page:" + page  + "| " + picture.toString());
					logFileInfo(sourceFileUrl.append(sourceFilePrefix).append(picture.getUrl()).toString());
					//清空builder对象
					sourceFileUrl.delete(0, sourceFileUrl.length());
				}
				pictureList.clear();
			}
		}
	}
}
