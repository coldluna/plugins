package org.grace.luna.template;

import cn.hutool.http.Method;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.grace.luna.properties.SmsProperties;
import org.grace.luna.util.ArrayUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 腾讯云短信发送template
 *
 * @author Artest Wang
 * Created on 2022/9/21 11:49:29
 */
@Slf4j
@RequiredArgsConstructor
public class TencentSmsTemplate extends SmsTemplate {

    private final SmsProperties smsProperties;

    private static String[] standardizePhoneNumber(String phoneNumber) {
        String[] phones = phoneNumber.split(",");
        List<String> phoneList = Arrays.stream(phones).map(phone -> "+86" + phone).collect(Collectors.toList());
        return ArrayUtil.toArray(phoneList, String.class);
    }

    @Override
    public boolean sendMessage(@NonNull String phoneNumber, @NonNull String signature,
                               @NonNull String templateCode, String templateParam) {

        // 构建请求
        SendSmsRequest request = new SendSmsRequest();
        request.setSmsSdkAppId(smsProperties.getAppId());
        request.setSignName(signature);
        request.setTemplateId(templateCode);
        if (StringUtils.isBlank(templateParam)) {
            request.setTemplateParamSet(null);
        } else {
            JSONObject param = JSONObject.parseObject(templateParam);
            request.setTemplateParamSet(ArrayUtil.toArray(param.values(), i -> (String) i, String.class));
        }

        request.setPhoneNumberSet(standardizePhoneNumber(phoneNumber));

        // 发送请求
        try {
            SmsClient client = this.client();
            SendSmsResponse response = client.SendSms(request);
            log.info("[发送短信] -> 返回结果为{}", SendSmsResponse.toJsonString(response));
            return true;
        } catch (TencentCloudSDKException | ClientException e) {
            log.error("[发送短信] -> 发送短信发生异常", e);
        }

        return false;
    }

    public SmsClient client() throws ClientException {
        // 初始化credential
        Credential credential = new Credential(smsProperties.getAccessKeyId(), smsProperties.getAccessKeySecret());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setReqMethod(Method.POST.name());
        httpProfile.setConnTimeout(60);
        httpProfile.setEndpoint(smsProperties.getDomain());

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);

        return new SmsClient(credential, smsProperties.getRegion(), clientProfile);
    }

}
