/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Participant;
import com.dtt.repositories.ParticipantRepository;
import com.dtt.services.ParticipantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienTu
 */

@Service
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public List<Participant> getParticipants() {
        return this.participantRepository.getParticipants();
    }

    @Override
    public Participant findByName(String name) {
        return this.participantRepository.findByName(name);
    }

    @Override
    public void addOrUpdate(Participant p) {
        this.participantRepository.addOrUpdate(p);
    }

    @Override
    public Participant getParticipantById(int id) {
        return this.participantRepository.getParticipantById(id);
    }

    @Override
    public void delete(Participant c) {
        this.participantRepository.delete(c);
    }
    
}
