package com.sanchi.service;

import com.sanchi.domain.VerificationType;
import com.sanchi.model.ForgotPasswordToken;
import com.sanchi.model.User;

public interface ForgotPasswordService {
    ForgotPasswordToken createToken(User user,
                                    String id, String otp,
                                    VerificationType verificationType,
                                    String sendTo) ;


    ForgotPasswordToken findById(String id);
    ForgotPasswordToken findByUser(Long userId);
    void deleteToken(ForgotPasswordToken token);
}
