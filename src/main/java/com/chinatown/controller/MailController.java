package com.chinatown.controller;

import com.chinatown.entity.Activity;
import com.chinatown.entity.Activity_Car;
import com.chinatown.entity.CT_User;
import com.chinatown.service.ActivityCarService;
import com.chinatown.service.ActivityService;
import com.chinatown.service.Impl.ActivityCarServiceImpl;
import com.chinatown.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import freemarker.template.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CrossOrigin(origins = "*")
@SuppressWarnings("ALL")
@RestController
@RequestMapping(value = "/chinatown")
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private Configuration freemarkerConfiguration;

    @Resource
    ActivityCarServiceImpl activity_car_service;
    @Resource
    UserInformationService userInformationService;

    @GetMapping("/simple/{id}")
    public String simple(@PathVariable("id") Optional<Long> id, HttpServletRequest request) {

        Activity_Car activityCar = activity_car_service.getActivitiesById(id.get());

        System.out.println(activityCar);
        System.out.println(activityCar.getEvent_sponsor_id());

        CT_User activity_sponsor = userInformationService.selectByPrimaryKey(activityCar.getEvent_sponsor_id());

        System.out.println(activity_sponsor);

        String setToEmail = activity_sponsor.getEmail();

        CT_User login_user = (CT_User)request.getSession().getAttribute("userInformation");

        String setFromEmail = login_user.getEmail();

        // 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人邮箱
        message.setFrom(setFromEmail);
        // 收信人邮箱
        message.setTo(setToEmail);
        // 邮件主题
        message.setSubject("Apply" + activityCar.getTitle() + "!");
        // 邮件内容
        message.setText(login_user.getUsername()
                + " is intereted in your activity !"
                + "My address is " + login_user.getAddress()
        );
        this.javaMailSender.send(message);
        return "success";
    }
}
