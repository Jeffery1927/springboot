package com.chinatown.service;

import com.chinatown.entity.Activity;
import java.util.List;

public interface ActivityService {

    List<Activity> getAllActivities();

    Activity getActivityById(Long id) ;

    void deleteActivityById(Long id) ;

    Activity createOrUpdateActivity(Activity entity);
}


