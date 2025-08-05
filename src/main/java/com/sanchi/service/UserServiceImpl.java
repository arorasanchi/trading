package com.sanchi.service;

import com.sanchi.config.JwtProvider;
import com.sanchi.domain.VerificationType;
import com.sanchi.model.TwoFactAuth;
import com.sanchi.model.User;
import com.sanchi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new Exception("user not found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new Exception("user not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user =userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("user not found");
        }
        return user.get();

    }

    @Override
    public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user) {
        TwoFactAuth twoFactAuth=new TwoFactAuth();
        twoFactAuth.setEnabled(true);
        twoFactAuth.setSendTo(verificationType);

        user.setTwoFactAuth(twoFactAuth);
        return userRepository.save(user);

    }


    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);

        return userRepository.save(user);
    }
}
