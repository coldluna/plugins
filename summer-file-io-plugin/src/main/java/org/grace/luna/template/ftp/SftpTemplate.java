package org.grace.luna.template.ftp;

import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * sftp操作template
 *
 * @author Artest
 * Created on 2022/8/31 10:52:07
 */
@Slf4j
@RequiredArgsConstructor
public class SftpTemplate extends FileTemplate {

    private final Sftp sftp;

    private final ChannelSftp channel;

    @Override
    public boolean upload(String fileName, String path, InputStream inputStream) {
        try {
            this.channel.put(inputStream, path + fileName);
            return true;
        } catch (SftpException e) {
            log.error("An exception occurred when upload file via sftp!", e);
            return false;
        }
    }

    @Override
    public void download(String fileName, String path, OutputStream outputStream) {
        try {
            this.channel.get(path + fileName, outputStream);
        } catch (SftpException e) {
            log.error("An exception occurred when download file via sftp!", e);
        }
    }

    @Override
    public boolean cd(String directory) {
        return sftp.cd(directory);
    }

    @Override
    public String pwd() {
        return sftp.pwd();
    }

    @Override
    public boolean mkdir(String directory) {
        return sftp.mkdir(directory);
    }

    @Override
    public List<String> ls(String path) {
        return sftp.ls(path);
    }

    @Override
    public boolean delFile(String path) {
        return sftp.delFile(path);
    }

    @Override
    public boolean delDir(String dirPath) {
        return sftp.delDir(dirPath);
    }
}
