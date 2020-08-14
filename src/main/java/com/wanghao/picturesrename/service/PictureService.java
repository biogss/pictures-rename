package com.wanghao.picturesrename.service;

import com.wanghao.picturesrename.entity.Picture;
import com.wanghao.picturesrename.entity.User;

import java.util.List;

/**
 * 图片服务类
 * @author wanghao
 */
public interface PictureService {

	/**
	 * 查询身份证正面和大头照
	 * @param page 页数，从1开始
	 * @param pageSize 每页条数
	 * @return List<Picture>
	 */
	List<Picture> getPictures(User user);

	/**
	 * 查询公安照
	 * @param idNo 身份证号码
	 * @return Picture
	 */
	List<Picture> getPolicePhoto(String idNo);
}
