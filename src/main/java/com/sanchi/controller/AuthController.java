package com.sanchi.controller;
import com.sanchi.config.JwtProvider;
import com.sanchi.model.TwoFactOTP;
import com.sanchi.model.User;


import com.sanchi.repository.UserRepository;
import com.sanchi.response.AuthResponse;
import com.sanchi.service.CustomUserDetailsService;
import com.sanchi.service.EmailService;
import com.sanchi.service.TwoFactorOtpService;
import com.sanchi.service.WatchlistService;
import com.sanchi.utils.OtpUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;

    @Autowired
    private EmailService emailService;
    @Autowired

    private WatchlistService watchlistService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {

        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist != null) {
            throw new Exception("email is already being accessed");

        }

        User newuser = new User();
        newuser.setEmail(user.getEmail());
        newuser.setPassword(user.getPassword());
        newuser.setFullname(user.getFullname());
        User saveduser = userRepository.save(newuser);

        watchlistService.createWatchlist(saveduser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()

        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("register success");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }




    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {
        String userName = user.getEmail();
        String password = user.getPassword();
        Authentication auth = authenticate(userName,password);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);
        User authUser = userRepository.findByEmail(userName);




        if(user.getTwoFactAuth().isEnabled()){
            AuthResponse res =new AuthResponse();
            res.setMessage("Two factor auth is enabled");
            res.setTwoFactorAuthEnabled(true);
            String otp = OtpUtils.generateOTP();

            TwoFactOTP oldTwoFactOtp = twoFactorOtpService.findByUser(authUser.getId());
            if(oldTwoFactOtp!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactOtp);
            }




            TwoFactOTP newTwoFactOTP = twoFactorOtpService.createTwoFactorOtp(
                    authUser,otp,jwt);
            emailService.sendVerificationOtpEmail(userName,otp);





            res.setSession(newTwoFactOTP.getId());
            return  new ResponseEntity<>(res,HttpStatus.ACCEPTED);
        }


        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login success");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
        if (userDetails == null) {
            throw new BadCredentialsException("invalid username");
        }
        if (!password.matches(userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }
    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySignInOtp(
            @PathVariable String otp,
            @RequestParam String id) throws Exception {
        TwoFactOTP twoFactOTP=twoFactorOtpService.findById(id);

        if(twoFactorOtpService.verifyTwoFactorOtp(twoFactOTP,otp)){
            AuthResponse res = new AuthResponse();
            res.setMessage("Two factor authentication verified");
            res.setTwoFactorAuthEnabled(true);
            res.setJwt(twoFactOTP.getJwt());
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        throw new Exception("invalid otp");
    }
}
