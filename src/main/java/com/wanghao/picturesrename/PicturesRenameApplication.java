package com.wanghao.picturesrename;

import com.wanghao.picturesrename.config.PicConfig;
import com.wanghao.picturesrename.entity.Picture;
import com.wanghao.picturesrename.function.PictureRename;
import com.wanghao.picturesrename.function.ReadFile;
import com.wanghao.picturesrename.service.PictureService;
import com.wanghao.picturesrename.service.VideoService;
import com.wanghao.picturesrename.util.FileUtil;
import com.wanghao.picturesrename.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * 图片名称转换启动类
 * @author wanghao
 */
@SpringBootApplication
public class PicturesRenameApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger("main");
		SpringApplication.run(PicturesRenameApplication.class, args);
		ApplicationContext context = SpringUtil.getApplicationContext();
		PicConfig picConfig = context.getBean(PicConfig.class);
		int totalPage = picConfig.getTotalPage();
		int pageSize = picConfig.getPageSize();
		String sourceFilePrefix = picConfig.getSourceFilePrefix();
		String newFilePrefix = picConfig.getNewFilePrefix();
		logger.info("totalPage:" + totalPage + "| pageSize:" + pageSize +
				"| sourceFilePrefix:" + sourceFilePrefix + "| newFilePrefix:" + newFilePrefix);

		String business = picConfig.getBusiness();
		if ("pic-copy-rename".equals(business)) {
			PictureRename pictureRename = context.getBean(PictureRename.class);
			pictureRename.pictureRename(totalPage, pageSize, sourceFilePrefix, newFilePrefix);
		}else if ("read-file".equals(business)) {
			ReadFile readFile = context.getBean(ReadFile.class);
			readFile.execReadFile(totalPage, pageSize, sourceFilePrefix);
		}
	}
}
