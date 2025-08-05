package com.sanchi.request;

import com.sanchi.domain.VerificationType;
import lombok.Data;

@Data

public class ForgotPasswordTokenRequest {
    private String sendTo;
    private VerificationType verificationType;
}
