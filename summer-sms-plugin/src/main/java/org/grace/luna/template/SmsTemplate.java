package org.grace.luna.template;


import org.grace.luna.principle.SmsOperations;

/**
 * @author Artest Wang
 * Created on 2022/9/21 11:47:57
 */
public abstract class SmsTemplate implements SmsOperations {

    static {
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
    }

}
