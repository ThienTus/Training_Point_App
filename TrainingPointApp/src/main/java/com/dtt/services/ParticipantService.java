/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Participant;
import java.util.List;

/**
 *
 * @author ThienTu
 */

public interface ParticipantService {
    List<Participant> getParticipants();
    Participant findByName(String name);
    void addOrUpdate(Participant p);
    Participant getParticipantById(int id);
    void delete(Participant c);
}
