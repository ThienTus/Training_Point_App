/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Comment;
import java.util.List;

/**
 *
 * @author ThienTu
 */

public interface CommentRepository {
    List<Comment> getCommentsByActivityId(int activityId, int page);
    void saveOrUpdate(Comment comment);
    void delete(int id);
    Comment getCommentById(int id);
    int countLikes(int id);
    boolean isUserLikedComment(Integer commentId, String username);
}
