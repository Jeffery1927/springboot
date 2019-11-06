package com.chinatown.service.Impl;

import com.chinatown.entity.Activity;

import com.chinatown.repository.ActivityRepository;
import com.chinatown.repository.ActivityRepository2;
import com.chinatown.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService{

    @Resource
    ActivityRepository repository;

    public List<Activity> getAllActivities() {
        List<Activity> result = (List<Activity>) repository.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Activity>();
        }
    }

    public Activity getActivityById(Long id)  {
        Optional<Activity> activity = repository.findById(id);

        if (activity.isPresent()) {
            System.out.println(activity.get());
            return activity.get();
        } else {
            return null;
//            throw new RecordNotFoundException("No activity record exist for given id");
        }
    }

    public void deleteActivityById(Long id)  {
        Optional<Activity> activity = repository.findById(id);

        if (activity.isPresent()) {
            repository.deleteById(id);
        } else {
//            throw new RecordNotFoundException("No activity record exist for given id");
        }
    }

    public Activity createOrUpdateActivity(Activity entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);
            return entity;
        } else {
            Optional<Activity> activity = repository.findById(entity.getId());
            if (activity.isPresent()) {
                Activity activity_entity = activity.get();
                activity_entity.setAddress(entity.getAddress());
                activity_entity.setAttend_people_id(entity.getAttend_people_id());
                activity_entity.setEvent_sponsor_id(entity.getEvent_sponsor_id());
                activity_entity.setNeed_food(entity.getNeed_food());
                activity_entity.setNeed_skill(entity.getNeed_skill());
                activity_entity.setProvide(entity.getProvide());
                activity_entity.setEnd_date(entity.getEnd_date());
                activity_entity.setStatus(entity.getStatus());
                activity_entity.setTitle(entity.getTitle());
                activity_entity = repository.save(activity_entity);
                return activity_entity;
            } else {
                entity = repository.save(entity);
                return entity;
            }
        }
    }
}
