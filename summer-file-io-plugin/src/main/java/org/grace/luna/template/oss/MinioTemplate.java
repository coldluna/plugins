package org.grace.luna.template.oss;

import cn.hutool.core.collection.CollectionUtil;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.grace.luna.model.Bucket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Artest
 * Created on 2022/8/31 15:46:18
 */
@RequiredArgsConstructor
public class MinioTemplate extends OssTemplate {

    private final MinioClient minioClient;

    @SneakyThrows
    @Override
    public void createBucket(String bucketName) {
        boolean exist = this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exist) {
            this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    @SneakyThrows
    @Override
    public List<Bucket> listBucket() {
        List<io.minio.messages.Bucket> buckets = this.minioClient.listBuckets();
        if (CollectionUtil.isNotEmpty(buckets)) {
            return buckets.stream().map(b -> new Bucket().setName(b.name()).setCreationDate(Date.from(b.creationDate().toInstant()))).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @SneakyThrows
    @Override
    public void removeBucket(String bucketName) {
        this.minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    @SneakyThrows
    @Override
    public String getObjectUrl(String bucketName, String objectName, Long leaseTime) {
        return this.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(objectName).expiry(leaseTime.intValue()).build());
    }

    @SneakyThrows
    @Override
    public InputStream getObject(String bucketName, String objectName) {
        return this.minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    @SneakyThrows
    @Override
    public void putObject(String bucketName, String objectName, InputStream inputStream) throws IOException {
        this.minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName)
                .stream(inputStream, inputStream.available(), -1).build());
    }

    @SneakyThrows
    @Override
    public void removeObject(String bucketName, String objectName) {
        this.minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
