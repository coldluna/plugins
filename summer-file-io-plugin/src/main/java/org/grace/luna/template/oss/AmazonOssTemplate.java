package org.grace.luna.template.oss;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.grace.luna.model.Bucket;
import org.grace.luna.model.Owner;
import org.grace.luna.util.ObjectUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Artest
 * Created on 2022/8/30 11:43:42
 */
@RequiredArgsConstructor
public class AmazonOssTemplate extends OssTemplate {

    private final AmazonS3 amazon;

    @Override
    public void createBucket(String bucketName) {
        if (!this.amazon.doesBucketExistV2(bucketName)) {
            this.amazon.createBucket(bucketName);
        }
    }

    @Override
    public List<Bucket> listBucket() {
        List<com.amazonaws.services.s3.model.Bucket> buckets = this.amazon.listBuckets();
        if (CollectionUtil.isNotEmpty(buckets)) {

            return buckets.stream().map(b -> {
                Bucket bucket = new Bucket();
                com.amazonaws.services.s3.model.Owner owner = b.getOwner();
                if (!ObjectUtil.worthless(owner)) {
                    bucket.setOwner(new Owner().setId(owner.getId()).setDisplayName(owner.getDisplayName()));
                }
                bucket.setName(b.getName());
                bucket.setCreationDate(b.getCreationDate());
                return bucket;
            }).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public void removeBucket(String bucketName) {
        this.amazon.deleteBucket(bucketName);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName, Long leaseTime) {
        URL url = this.amazon.generatePresignedUrl(bucketName, objectName, DateUtil.offset(new Date(), DateField.SECOND, leaseTime.intValue()));
        return url.toString();
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) {
        return this.amazon.getObject(bucketName, objectName).getObjectContent();
    }

    @Override
    public void putObject(String bucketName, String objectName, InputStream inputStream) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(inputStream.available());
        this.amazon.putObject(bucketName, objectName, inputStream, objectMetadata);
    }

    @Override
    public void removeObject(String bucketName, String objectName) {
        this.amazon.deleteObject(bucketName, objectName);
    }
}
