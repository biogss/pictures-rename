package com.wanghao.picturesrename.util;

import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MimeTypeUtil {

    /**
     * imagePattern ：图片正则
     * 利用正则的预编译效果，提高效率
     */
     private static Pattern imagePattern = Pattern.compile("image/.*");
    /**
     * videoPattern 视频正则
     * 利用正则的预编译效果，提高效率
     */
    private static Pattern videoPattern = Pattern.compile("video/.*");

    /**
            * zipPattern ：压缩文件正则
     * 利用正则的预编译效果，提高效率
     */
    private static Pattern zipPattern = Pattern.compile("zip/.*");

    /**
     * textPattern ：文本文件正则
     * 利用正则的预编译效果，提高效率
     */
    private static Pattern textPattern = Pattern.compile("text/.*");

    public static String getMimeType(File file) {
        if (file.isDirectory()) {
            return "the target is a directory";
        }

        AutoDetectParser parser = new AutoDetectParser();
        parser.setParsers(new HashMap<MediaType, Parser>());
        Metadata metadata = new Metadata();
        metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());
        try (InputStream stream = new FileInputStream(file)) {
            parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        }catch (Exception e){
            throw new RuntimeException();
        }
        return metadata.get(HttpHeaders.CONTENT_TYPE);
    }

    /**
     * 判断文件是否是图片
     * @param file
     * @return
     */
    public static boolean isImage(File file){
        String type = getMimeType(file);
        System.out.println(type);
        Matcher m = imagePattern.matcher(type);
        return m.matches();
    }

    /**
     * 判断文件是否为视频
     * @param file
     * @return
     */
    public static boolean isVideo(File file){
        String type = getMimeType(file);
        System.out.println(type);
        Matcher m = videoPattern.matcher(type);
        return m.matches();
    }

    /**
     * 判断文件是否为压缩文件
     * @param file
     * @return
     */
    public static boolean isZip(File file){
        String type = getMimeType(file);
        System.out.println(type);
        Matcher m = zipPattern.matcher(type);
        return m.matches();
    }

    /**
     * 判断文件是否为文本文件
     * @param file
     * @return
     */
    public static boolean isText(File file){
        String type = getMimeType(file);
        System.out.println(type);
        Matcher m = textPattern.matcher(type);
        return m.matches();
    }
}
