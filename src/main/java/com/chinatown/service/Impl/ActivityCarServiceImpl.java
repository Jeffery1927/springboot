package com.chinatown.service.Impl;

import com.chinatown.entity.Activity_Car;
import com.chinatown.repository.ActivityCarRepository;
import com.chinatown.service.ActivityCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityCarServiceImpl implements ActivityCarService {

    @Resource
    ActivityCarRepository activityCarRepository;

    public List<Activity_Car> getActivitiesCar(String id) {
        List<Activity_Car> result = (List<Activity_Car>) activityCarRepository.getActivitiesCar(id);
            return result;
    }

    public Activity_Car getActivitiesById(Long id) {
         return activityCarRepository.getActivitiesById(id);
    }
}
