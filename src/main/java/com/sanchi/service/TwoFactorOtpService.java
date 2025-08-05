package com.sanchi.service;

import com.sanchi.model.TwoFactOTP;
import com.sanchi.model.User;

public interface TwoFactorOtpService {
    TwoFactOTP createTwoFactorOtp(User user,String otp,String jwt);
    TwoFactOTP findByUser(Long userId);
    TwoFactOTP findById(String id);
    boolean verifyTwoFactorOtp(TwoFactOTP twoFactOtp,String otp);
    void deleteTwoFactorOtp(TwoFactOTP twoFactOtp);

}
