package org.grace.luna.principle;

import org.grace.luna.model.Bucket;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 对象存储操作interface
 *
 * @author Artest
 * Created on 2022/8/30 10:29:59
 */
public interface OssOperations {

    /**
     * 创建bucket
     *
     * @param bucketName
     */
    void createBucket(String bucketName);

    /**
     * 获取所有的bucket
     *
     * @return
     */
    List<Bucket> listBucket();

    /**
     * 获取bucket
     *
     * @param bucketName
     * @return
     */
    Bucket getBucket(String bucketName);

    /**
     * 删除bucket
     *
     * @param bucketName
     */
    void removeBucket(String bucketName);

    /**
     * 获取文件链接
     *
     * @param bucketName
     * @param objectName
     * @param leaseTime  秒
     * @return
     */
    String getObjectUrl(String bucketName, String objectName, Long leaseTime);

    /**
     * 下载文件
     *
     * @param bucketName
     * @param objectName
     * @return
     */
    InputStream getObject(String bucketName, String objectName);

    /**
     * 上传文件，注意文件名应使用随机的唯一名称，否则会导致文件覆盖
     *
     * @param bucketName
     * @param objectName  全路径+文件名
     * @param inputStream
     * @throws IOException
     */
    void putObject(String bucketName, String objectName, InputStream inputStream) throws IOException;

    /**
     * 删除文件
     *
     * @param bucketName
     * @param objectName
     */
    void removeObject(String bucketName, String objectName);

}
