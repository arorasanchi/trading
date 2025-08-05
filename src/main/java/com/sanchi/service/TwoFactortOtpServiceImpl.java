package com.sanchi.service;

import com.sanchi.model.TwoFactOTP;
import com.sanchi.model.User;
import com.sanchi.repository.TwoFactorOtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TwoFactortOtpServiceImpl implements TwoFactorOtpService {
    @Autowired
    private TwoFactorOtpRepository twoFactorOtpRepository;
    @Override
    public TwoFactOTP createTwoFactorOtp(User user, String otp, String jwt) {
        UUID uuid=UUID.randomUUID();
        String id = uuid.toString();
        TwoFactOTP twoFactOTP=new TwoFactOTP();
        twoFactOTP.setOtp(otp);
        twoFactOTP.setId(id);
        twoFactOTP.setJwt(jwt);
        twoFactOTP.setUser(user);
        return twoFactorOtpRepository.save(twoFactOTP);




    }

    @Override
    public TwoFactOTP findByUser(Long userId) {
        return twoFactorOtpRepository.findByUserId(userId);
    }

    @Override
    public TwoFactOTP findById(String id) {
        Optional<TwoFactOTP> opt = twoFactorOtpRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public boolean verifyTwoFactorOtp(TwoFactOTP twoFactOtp, String otp) {
       return twoFactOtp.getOtp().equals(otp);
    }

    @Override
    public void deleteTwoFactorOtp(TwoFactOTP twoFactOtp) {
         twoFactorOtpRepository.delete(twoFactOtp);
    }
}
