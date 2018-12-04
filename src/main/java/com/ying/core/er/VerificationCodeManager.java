package com.ying.core.er;

import com.ying.core.data.VerificationCode;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bvvy
 * @date 2018/12/3
 */
public class VerificationCodeManager {
    private static Map<String, VerificationCode> verifyCodeMap = new ConcurrentHashMap<>();

    public VerificationCode generate(String phoneNumber) {
        Random random = new Random();
        String code = String.valueOf(random.nextInt(900000) + 100000);
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setValue(code);
        verifyCodeMap.put(phoneNumber, verificationCode);
        return verificationCode;
    }

    public boolean validate(String phoneNumber ,String code) {
        VerificationCode verificationCode = verifyCodeMap.get(phoneNumber);
        return verificationCode != null && verificationCode.getValue().equals(code);
    }
}

