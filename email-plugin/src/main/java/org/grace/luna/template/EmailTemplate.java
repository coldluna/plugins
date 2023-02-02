package org.grace.luna.template;

import org.grace.luna.principle.EmailOperations;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Austin Wong
 * Created on 2022/8/26 15:13:44
 */
@Component
public class EmailTemplate implements EmailOperations {

    @Resource
    private MailProperties mailProperties;

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendTextualEmail(String subject, String content, String to, String... cc) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        if (!ObjectUtils.isEmpty(cc)) {
            message.setCc(cc);
        }
        javaMailSender.send(message);
    }

    @Override
    public void sendHtmlEmail(String subject, String content, String to, String... cc) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(mailProperties.getUsername());
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        if (!ObjectUtils.isEmpty(cc)) {
            messageHelper.setCc(cc);
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendAttachmentEmail(String subject, String content, String to, String filePath, String... cc) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(to);
        messageHelper.setFrom(mailProperties.getUsername());
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        if (!ObjectUtils.isEmpty(cc)) {
            messageHelper.setCc(cc);
        }

        // 附件
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        messageHelper.addAttachment(fileName, fileSystemResource);

        javaMailSender.send(mimeMessage);
    }
}
