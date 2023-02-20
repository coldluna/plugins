package org.grace.luna.properties;

import lombok.Data;
import org.grace.luna.enums.FileTransportScheme;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.Charset;

/**
 * 文件传输properties
 *
 * @author Artest
 * Created on 2022/8/31 13:44:44
 */
@Data
@ConfigurationProperties(prefix = "file.transport")
public class FileTransportProperties {

    private String host;

    private Integer port;

    private String user;

    private String password;

    private Charset charset = Charset.defaultCharset();

    private FileTransportScheme scheme;
}
