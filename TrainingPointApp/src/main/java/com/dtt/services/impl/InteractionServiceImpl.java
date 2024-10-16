/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Interaction;
import com.dtt.repositories.InteractionRepository;
import com.dtt.services.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienTu
 */

@Service
public class InteractionServiceImpl implements InteractionService {
    @Autowired
    private InteractionRepository interactionRepository;

    @Override
    public void save(Interaction i) {
        this.interactionRepository.save(i);
    }

    @Override
    public void delete(int id) {
        this.interactionRepository.delete(id);
    }

    @Override
    public Interaction getInteractionByUserIdAndActivityId(int userId, int activityId) {
        return this.interactionRepository.getInteractionByUserIdAndActivityId(userId, activityId);
    }

    @Override
    public Interaction getInteractionByUserIdAndCommentId(int userId, int commentId) {
        return this.interactionRepository.getInteractionByUserIdAndCommentId(userId, commentId);
    }
    
}
