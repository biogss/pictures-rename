package com.wanghao.picturesrename.dao;

import com.wanghao.picturesrename.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 图片DAO类
 * @author wanghao
 * @date 2020-08-06
 */
@Mapper
@Repository
public interface PicDao {

	/**
	 * 分页查询身份证和大头照
	 * @param userId 每页数据条数
	 * @param type 照片类型，2-大头照，3-身份证正面
	 * @return List<Picture>
	 */
	List<Picture> getPictures(@Param("userId") int userId, @Param("type") String type);

	/**
	 * 查询公安照
	 * @param idNo 身份证号码
	 * @return List<Picture>
	 */
	List<Picture> getPolicePhoto(@Param("idNo") String idNo);
}
