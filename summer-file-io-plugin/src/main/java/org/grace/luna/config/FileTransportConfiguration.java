package org.grace.luna.config;

import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import org.grace.luna.properties.FileTransportProperties;
import org.grace.luna.template.ftp.FileTemplate;
import org.grace.luna.template.ftp.FtpTemplate;
import org.grace.luna.template.ftp.SftpTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件传输配置类
 *
 * @author Artest
 * Created on 2022/8/31 13:43:27
 */
@Configuration
@EnableConfigurationProperties(FileTransportProperties.class)
@ConditionalOnProperty("file.transport.scheme")
public class FileTransportConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "file.transport", name = "scheme", havingValue = "ftp")
    public FileTemplate ftpTemplate(FileTransportProperties fileTransportProperties) {
        Ftp ftp = new Ftp(fileTransportProperties.getHost(),
                fileTransportProperties.getPort(),
                fileTransportProperties.getUser(),
                fileTransportProperties.getPassword(),
                fileTransportProperties.getCharset());
        ftp.init();
        return new FtpTemplate(ftp);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "file.transport", name = "scheme", havingValue = "sftp")
    public FileTemplate sftpTemplate(FileTransportProperties fileTransportProperties) {
        Session session = JschUtil.getSession(fileTransportProperties.getHost(),
                fileTransportProperties.getPort(),
                fileTransportProperties.getUser(),
                fileTransportProperties.getPassword());
        ChannelSftp channelSftp = JschUtil.openSftp(session);
        return new SftpTemplate(new Sftp(channelSftp, fileTransportProperties.getCharset()), channelSftp);
    }

}
