package org.grace.luna.principle;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件io操作interface
 *
 * @author Artest
 * Created on 2022/8/30 10:41:08
 */
public interface FileOperations {

    /**
     * 上传文件
     *
     * @param fileName
     * @param path
     * @param inputStream
     * @return
     */
    boolean upload(String fileName, String path, InputStream inputStream);

    /**
     * 下载文件
     *
     * @param fileName
     * @param path
     * @param outputStream
     * @return
     */
    void download(String fileName, String path, OutputStream outputStream);
}
