package com.chinatown.service;

import com.chinatown.entity.Activity;
import com.chinatown.entity.Activity_Car;

import java.util.List;

public interface ActivityService {

    List<Activity> getAllActivities();

    Activity getActivityById(Long id) ;

    void deleteActivityById(Long id) ;

    Activity createOrUpdateActivity(Activity entity);

    String getSponserIdByActivityID(Long id);

    Activity_Car insertSelective(Activity_Car activityCar);
}


