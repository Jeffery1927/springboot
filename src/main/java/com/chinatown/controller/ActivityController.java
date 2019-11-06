package com.chinatown.controller;
import com.chinatown.entity.Activity;

import com.chinatown.service.Impl.ActivityServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/chinatown")
public class ActivityController {

    @Resource
    ActivityServiceImpl service;

    @RequestMapping("/activities")
    public String getAllActivities(Model model){
        List<Activity> list = service.getAllActivities();
        model.addAttribute("activities",list);
        return "list-activities";
    }

    @RequestMapping(path = {"/edit","/edit/id/{id}"})
    public String editActivityById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()){
            Activity activity = service.getActivityById(id.get());
            model.addAttribute("activity",activity);
            System.out.println("fjafdsaljfl");
        } else {
            model.addAttribute("activity",new Activity());
        }
        return "add-edit-activity";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteActivityById(Model model, @PathVariable("id") Long id) {
        service.deleteActivityById(id);
        return "redirect:/chinatown/activities";
    }

    @RequestMapping(path = "/createActivity", method = RequestMethod.POST)
    public String createOrUpdateActivity(Activity activity)
    {
        service.createOrUpdateActivity(activity);
        return "redirect:/chinatown/activities";
    }

}
