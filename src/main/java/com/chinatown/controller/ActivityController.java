package com.chinatown.controller;
import com.chinatown.entity.Activity;

import com.chinatown.entity.Activity_Car;
import com.chinatown.entity.CT_User;
import com.chinatown.response.BaseResponse;
import com.chinatown.service.Impl.ActivityCarServiceImpl;
import com.chinatown.service.Impl.ActivityServiceImpl;

import com.chinatown.tool.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/chinatown")
public class ActivityController {

    private static Logger log = LoggerFactory.getLogger(ActivityController.class);

    @Resource
    ActivityServiceImpl service;
    @Resource
    ActivityCarServiceImpl service_car;

    @RequestMapping("/activities")
    public String getAllActivities(HttpServletRequest request,Model model){
        CT_User userInformation = (CT_User) request.getSession().getAttribute("userInformation");
        if (StringUtils.getInstance().isNullOrEmpty(userInformation)) {
            return "redirect:/login.do";
        }
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

    //添加到购物车
    @RequestMapping(value = "/attendActivity", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse insertGoodsCar(HttpServletRequest request, @RequestBody Activity activity) {

        String ActivitySponserId = service.getSponserIdByActivityID(activity.getId());
        CT_User userInformation = (CT_User) request.getSession().getAttribute("userInformation");
        if (StringUtils.getInstance().isNullOrEmpty(userInformation)) {
            return BaseResponse.fail();
        }
        String uid = userInformation.getId();
        Activity_Car activityCar = new Activity_Car();
        activityCar.setEvent_sponsor_id(ActivitySponserId);
        activityCar.setAttend_people_id(uid);
        activityCar.setId(activity.getId());
        activityCar.setAddress(userInformation.getAddress());
        activityCar.setModified(new Date());
        service.insertSelective(activityCar);
        return BaseResponse.success();
    }

    //参与活动一览
    @RequestMapping("/activities_car")
    public String getActivitiesCar(HttpServletRequest request ,Model model){
        CT_User userInformation = (CT_User) request.getSession().getAttribute("userInformation");
        String uid = userInformation.getId();
        System.out.println(uid);
        List<Activity_Car> list = service_car.getActivitiesCar(uid);
        model.addAttribute("activities_car",list);
        return "activity_car";
    }
}
