/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Activity;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ThienTu
 */

public interface ActivityRepository {
    List<Activity> getActivities();
    Activity getActivityById(int id);
    void addOrUpdate(Activity activity);
    List<Activity> findFilteredActivities(Map<String, String> params);
    void deleteActivity(int id);
    int countLikes(int activityId);
    boolean isUserLikedActivity(Integer activityId, String username);
}
