package org.grace.luna.template;

import cn.hutool.core.date.DateTime;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grace.luna.properties.SmsProperties;

/**
 * 阿里云短信发送template
 *
 * @author Artest Wang
 * Created on 2022/9/21 11:49:29
 */
@Slf4j
@RequiredArgsConstructor
public class AliSmsTemplate extends SmsTemplate {

    private final SmsProperties smsProperties;

    @Override
    public boolean sendMessage(@NonNull String phoneNumber, @NonNull String signature,
                               @NonNull String templateCode, String templateParam) {

        // 构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsProperties.getDomain());
        request.setSysVersion(DateTime.now().toString());
        request.setSysAction("sendMessage");

        request.putQueryParameter("RegionId", smsProperties.getRegion());
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", signature);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);

        // 发送请求
        try {
            IAcsClient client = this.client();
            CommonResponse response = client.getCommonResponse(request);
            log.info("[发送短信] -> 返回结果为{}", response.getData());
            return true;
        } catch (ClientException e) {
            log.error("[发送短信] -> 发送短信发生异常", e);
        }

        return false;
    }

    public IAcsClient client() throws ClientException {
        // 初始化AcsClient
        DefaultProfile profile = DefaultProfile.getProfile(smsProperties.getRegion(),
                smsProperties.getAccessKeyId(), smsProperties.getAccessKeySecret());
        DefaultProfile.addEndpoint(smsProperties.getRegion(), smsProperties.getProduct(), smsProperties.getEndpoint());
        return new DefaultAcsClient(profile);
    }

}
