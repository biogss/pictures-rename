package com.wanghao.picturesrename.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 文件读取和写入
 * @author 王浩
 * @date 2020-02-02
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static Boolean readAndWriterFile(String sourceFileUrl, String newFileUrl){
        logger.info("sourceFileUrl:" + sourceFileUrl);
        logger.info("newFileUrl:" + newFileUrl);
        Boolean result = false;
        File newFile = new File(newFileUrl);
        File sourceFile = new File(sourceFileUrl);
        try {
            //判断文件是否存在
            if (!sourceFile.exists()) {
                logger.error("源文件不存在：" + sourceFile.getAbsolutePath());
                return false;
            }
            //判断目标文件是否存在，不存在创建一个新的文件
            if (!newFile.exists()) {
                if (!newFile.createNewFile()) {
                    logger.error("目标文件不存在，创建新的目标文件失败：" + newFile.getAbsolutePath());
                }
            }
        }catch (IOException e) {
            logger.error("创建新文件发生异常：" + e.getMessage());
            return false;
        }
        //文本文件是有字符流处理，视频，图片等文件使用字节流处理
        if (MimeTypeUtil.isText(sourceFile)){
            logger.info("处理文本文件");
            result = doText(sourceFile, newFile);
        }else {
            logger.info("处理非文本文件");
            result = doStream(sourceFile, newFile);
        }

        return result;
    }

    /**
     * 处理视频或者图片信息
     * @param sourceFile
     * @param newFile
     * @return
     */
    private static Boolean doStream(File sourceFile, File newFile) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long startTime = System.currentTimeMillis();
        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFile));
            bos = new BufferedOutputStream(new FileOutputStream(newFile));
            byte[] temp = new byte[1024];
            int len = 0;
            while ((len = bis.read(temp)) != -1) {
                bos.write(temp);
            }
        } catch (IOException e) {
            logger.error("读写文件发生异常：" + e.getMessage());
            return false;
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                logger.error("关闭资源文件发生异常：" + e.getMessage());
            }
        }
        long endTime = System.currentTimeMillis();
        logger.debug("非文本文件处理花费时间：" + (endTime - startTime) + "ms");
        return true;
    }

    /**
     * 处理文本文件
     * @param newFile
     * @param sourceFile
     * @return
     */
     private static Boolean doText(File sourceFile, File newFile){
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        long startTime = System.currentTimeMillis();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile),
                    StandardCharsets.UTF_8));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile),
                    StandardCharsets.UTF_8));
            if (! bufferedReader.ready()) {
                logger.error("文件流暂时无法读取！");
                return false;
            }
            String newFileStr;
            while ((newFileStr = bufferedReader.readLine()) != null) {
                bufferedWriter.write(newFileStr + "\n");
            }
        } catch (FileNotFoundException e) {
            logger.error("没有找到对应的源文件：" + sourceFile);
            return false;
        } catch (IOException e) {
            logger.error("读写文件发生异常：" + e.getMessage());
            return false;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                logger.error("关闭资源文件发生异常：" + e.getMessage());
            }
        }
        long endTime = System.currentTimeMillis();
        logger.debug("文本处理花费时间：" + (endTime - startTime) + "ms");
        return true;
    }

    public static boolean isExists(String fileUrl) {
         File file = new File(fileUrl);
         return file.exists();
    }

    public static void main(String[] args) {
//        System.out.println(FileUtil.readAndWriterFile(
//                "C:\\Users\\biogss\\Desktop\\1234.jpg",
//                "D:\\abc\\王浩.jpg"));
//        System.out.println(FileUtil.isExists("C:\\Users\\biogss\\Desktop\\1234.jpg"));
    }
}
