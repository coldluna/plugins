package com.ruolin.manage.facade;

import javax.mail.MessagingException;

/**
 * @author Austin Wong
 * @version 1.0.0
 * @since JDK1.8
 * Created on 2022/8/26 15:07:30
 */
public interface EmailOperations {

    /**
     * 发送普通文本邮件
     *
     * @param subject
     * @param content
     * @param to
     * @param cc
     */
    void sendTextualEmail(String subject, String content, String to, String... cc);

    /**
     * 发送html邮件
     *
     * @param subject
     * @param content
     * @param to
     * @param cc
     */
    void sendHtmlEmail(String subject, String content, String to, String... cc) throws MessagingException;

    /**
     * 发送附件邮件
     *
     * @param subject
     * @param content
     * @param to
     * @param cc
     * @param filePath
     */
    void sendAttachmentEmail(String subject, String content, String to, String filePath, String... cc) throws MessagingException;
}
