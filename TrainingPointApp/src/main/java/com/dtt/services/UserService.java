/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.services;

import com.dtt.dto.AssistantDTO;
import com.dtt.dto.StudentUserDTO;
import com.dtt.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ThienTu
 */

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    int getIdByUsername(String username);
    public void saveUser(User user);
    void updateUserRole(int userId, String role);
    boolean authUser(String username, String password);
    StudentUserDTO getUserByUsernameDTO(String username);
    List<AssistantDTO> getAssistantsDTO();
}
