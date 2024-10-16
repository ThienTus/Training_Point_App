/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.User;
import java.util.List;

/**
 *
 * @author ThienTu
 */

public interface UserRepository {
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserById(int id);
    int getIdByUsername(String username);
    void saveUser(User user);
    void updateUserRole(int userId, String role);
    boolean authUser(String username, String password);
    List<User> getAssistantList();
}
