package org.grace.luna.template.oss;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import lombok.RequiredArgsConstructor;
import org.grace.luna.model.Bucket;
import org.grace.luna.model.Owner;
import org.grace.luna.util.ObjectUtil;

import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Artest
 * Created on 2022/8/30 11:38:12
 */
@RequiredArgsConstructor
public class AliOssTemplate extends OssTemplate {

    private final OSSClient ossClient;

    @Override
    public void createBucket(String bucketName) {
        if (!this.ossClient.doesBucketExist(bucketName)) {
            this.ossClient.createBucket(bucketName);
        }
    }

    @Override
    public List<Bucket> listBucket() {
        List<com.aliyun.oss.model.Bucket> buckets = this.ossClient.listBuckets();
        if (CollectionUtil.isNotEmpty(buckets)) {

            return buckets.stream().map(b -> {
                Bucket bucket = new Bucket();
                com.aliyun.oss.model.Owner owner = b.getOwner();
                if (!ObjectUtil.worthless(owner)) {
                    bucket.setOwner(new Owner().setId(owner.getId()).setDisplayName(owner.getDisplayName()));
                }
                bucket.setName(b.getName());
                bucket.setCreationDate(b.getCreationDate());
                bucket.setLocation(b.getLocation());
                return bucket;
            }).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public void removeBucket(String bucketName) {
        this.ossClient.deleteBucket(bucketName);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName, Long leaseTime) {
        return this.ossClient.generatePresignedUrl(bucketName, objectName, DateUtil.offset(new Date(), DateField.SECOND, leaseTime.intValue())).toString();
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) {
        OSSObject object = this.ossClient.getObject(bucketName, objectName);
        return object.getObjectContent();
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream inputStream) {
        this.ossClient.putObject(bucketName, objectName, inputStream);
    }

    @Override
    public void removeObject(String bucketName, String objectName) {
        this.ossClient.deleteObject(bucketName, objectName);
    }
}
