package com.sanchi.repository;

import com.sanchi.model.TwoFactOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactOTP,String> {
    TwoFactOTP findByUserId(Long  userId);

}
