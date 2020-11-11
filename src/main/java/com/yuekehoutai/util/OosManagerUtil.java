package com.yuekehoutai.util;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

public class OosManagerUtil {
    public static String uploadFile(MultipartFile multipartFile, String remotePath) throws Exception {

        // CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
        // DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        // 流转换 将MultipartFile转换为oss所需的InputStream
        InputStream fileContent = multipartFile.getInputStream();
        //获取文件名，对文件名做随机处理
        String fileName = multipartFile.getOriginalFilename();

        //这里可以修改文件的命名
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        // 加载配置文件，初始化OSSClient
        //  OSSConfigure ossConfigure = new OSSConfigure("/system.properties");
        OSSClient ossClient = new OSSClient(FilePath.endpoint, FilePath.accessKeyId,
                FilePath.accessKeySecret);
        System.err.println(ossClient.toString());
        // 定义二级目录
        //String remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\", "/") + "/";
        // 创建上传Object的Metadata
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentLength(fileContent.available());
//        objectMetadata.setContentEncoding("utf-8");
//        objectMetadata.setCacheControl("no-cache");
//        objectMetadata.setHeader("Pragma", "no-cache");
//        objectMetadata.setContentType(contentType(fileName.substring(fileName.lastIndexOf("."))));
//        objectMetadata.setContentDisposition("inline;filename=" + fileName);
        // 上传文件
        //System.err.print(remoteFilePath);
        //ossClient.putObject(FilePath.bucketName, remoteFilePath+fileName , fileContent, objectMetadata);
        ossClient.putObject(FilePath.bucketName, remotePath+"/"+fileName , fileContent);

        // 关闭OSSClient
        ossClient.shutdown();
        // 关闭io流
        fileContent.close();
        //System.err.println(FilePath.accessUrl + "/" +  fileName);
        return FilePath.accessUrl + "/" + remotePath + "/" + fileName;
    }

}
