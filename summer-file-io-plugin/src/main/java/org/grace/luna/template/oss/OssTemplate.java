package org.grace.luna.template.oss;

import org.grace.luna.model.Bucket;
import org.grace.luna.principle.OssOperations;

/**
 * @author Artest
 * Created on 2022/8/30 11:49:10
 */
public abstract class OssTemplate implements OssOperations {

    public Bucket getBucket(String bucketName) {
        return this.listBucket().stream().filter(b -> b.getName().equals(bucketName)).findAny().orElse(null);
    }

}
