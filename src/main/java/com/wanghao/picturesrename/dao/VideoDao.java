package com.wanghao.picturesrename.dao;

import com.wanghao.picturesrename.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 视频DAO
 */
@Mapper
@Repository
public interface VideoDao {

	/**
	 * 根据身份证信息获取视频信息
	 * @param idNo 身份证号码
	 * @return List<Video>
	 */
	List<Video> getVideoListByIdNo(@Param("userId") int userId);
}

