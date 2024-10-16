/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Otp;

/**
 *
 * @author ThienTu
 */

public interface OtpRepository {
    Otp findByEmail(String email);
    void save(Otp otp);
}
