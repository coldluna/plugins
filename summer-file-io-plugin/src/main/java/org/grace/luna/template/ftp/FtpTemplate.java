package org.grace.luna.template.ftp;

import cn.hutool.extra.ftp.Ftp;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * ftp操作template
 *
 * @author Artest
 * Created on 2022/8/31 10:38:37
 */
@RequiredArgsConstructor
public class FtpTemplate extends FileTemplate {

    private final Ftp ftp;

    @Override
    public boolean upload(String fileName, String path, InputStream inputStream) {
        return this.ftp.upload(path, fileName, inputStream);
    }

    @Override
    public void download(String fileName, String path, OutputStream outputStream) {
        this.ftp.download(path, fileName, outputStream);
    }

    @Override
    public boolean cd(String directory) {
        return ftp.cd(directory);
    }

    @Override
    public String pwd() {
        return ftp.pwd();
    }

    @Override
    public boolean mkdir(String directory) {
        return ftp.mkdir(directory);
    }

    @Override
    public List<String> ls(String path) {
        return ftp.ls(path);
    }

    @Override
    public boolean delFile(String path) {
        return ftp.delFile(path);
    }

    @Override
    public boolean delDir(String dirPath) {
        return ftp.delDir(dirPath);
    }
}
