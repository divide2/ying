package com.ying.core.er;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ying.core.data.VerificationCode;
import com.ying.core.data.properties.AliyunProperties;
import com.ying.core.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bvvy
 * @date 2018/12/3
 */
@Slf4j
@Component
public class VerificationCodeManager {
    private static Map<String, VerificationCode> verifyCodeMap = new ConcurrentHashMap<>();
    private final AliyunProperties aliyunProperties;

    public VerificationCodeManager(AliyunProperties aliyunProperties) {
        this.aliyunProperties = aliyunProperties;
    }

    public VerificationCode generate() {
        Random random = new Random();
        String code = String.valueOf(random.nextInt(900000) + 100000);
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        return verificationCode;
    }

    public void sendSms(String phoneNumber) {
        DefaultProfile profile = DefaultProfile.getProfile("default", aliyunProperties.getAccessKeyId(), aliyunProperties.getSecretAccessKey());
        IAcsClient client = new DefaultAcsClient(profile);
        VerificationCode verificationCode = generate();
        verifyCodeMap.put(phoneNumber, verificationCode);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "除以二");
        request.putQueryParameter("TemplateCode", "SMS_160571359");
        request.putQueryParameter("TemplateParam", Jsoner.toJson(verificationCode));
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.debug("send sms resp -> {},status -> {}", response, response.getHttpStatus());
        } catch (ClientException e) {
            log.error("error in send msm: {}", e.getMessage());
            throw new ValidationException("send_sms_fail");
        }
    }

    public boolean validate(String phoneNumber, String code) {
        VerificationCode verificationCode = verifyCodeMap.get(phoneNumber);
        return verificationCode != null && verificationCode.getCode().equals(code);
    }

    public void remove(String phoneNumber) {
        verifyCodeMap.remove(phoneNumber);
    }

}

