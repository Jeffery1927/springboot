package com.chinatown.service;

import com.chinatown.entity.Activity;
import com.chinatown.entity.Activity_Car;

import java.util.List;

public interface ActivityCarService {

    List<Activity_Car> getActivitiesCar(String id);

    Activity_Car getActivitiesById(Long id);
}