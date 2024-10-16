/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Otp;
import com.dtt.pojo.User;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

/**
 *
 * @author ThienTu
 */

public interface OtpService {
    Otp findByEmail(String email);
    void generateOTP(User user, String email);
    void sendOTPEmail(String email, String OTP) throws UnsupportedEncodingException, MessagingException;
    void clearOTP(Otp otp);
    Boolean isOtpExpired(Otp otp);
}
