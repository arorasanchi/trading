package com.sanchi.model;
import jakarta.persistence.Entity;
import com.sanchi.domain.VerificationType;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Data
public class TwoFactAuth {
    private boolean isEnabled = false;
    private VerificationType sendTo;
}
