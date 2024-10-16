/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.ParticipationType;
import java.util.List;

/**
 *
 * @author ThienTu
 */

public interface ParticipationTypeService {
    List<ParticipationType> getParticipationTypes();
    ParticipationType findByName(String name);
    void addOrUpdate(ParticipationType p);
    ParticipationType getParticipationTypeById(int id);
    void delete(ParticipationType c);
}
