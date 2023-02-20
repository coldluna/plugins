package org.grace.luna.principle;

import lombok.NonNull;

/**
 * @author Artest Wang
 * Created on 2022/9/20 18:04:37
 */
public interface SmsOperations {

    /**
     * 发送短信
     *
     * @param phoneNumber   手机号
     * @param signature     签名
     * @param templateCode  模板编号
     * @param templateParam 模板参数（JSON字符串）
     * @return
     */
    boolean sendMessage(@NonNull String phoneNumber, @NonNull String signature,
                        @NonNull String templateCode, String templateParam);


}
