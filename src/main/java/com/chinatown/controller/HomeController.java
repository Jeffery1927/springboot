package com.chinatown.controller;

import com.chinatown.entity.Activity;
import com.chinatown.entity.CT_User;
import com.chinatown.entity.User_reg;
import com.chinatown.service.Impl.ActivityServiceImpl;
import com.chinatown.token.TokenProccessor;
import com.chinatown.tool.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by qiang on 2019/10/28.
 */
@Controller
public class HomeController {

    @Resource
    ActivityServiceImpl service;

    @RequestMapping(value = {"/", "/home.do"})
    public String home(HttpServletRequest request, Model model) {
        CT_User userInformation = (CT_User) request.getSession().getAttribute("userInformation");
        // if user login,the session will have the "userInformation"
        if (!StringUtils.getInstance().isNullOrEmpty(userInformation)) {
            model.addAttribute("userInformation", userInformation);
        } else {
            userInformation = new CT_User();
            model.addAttribute("userInformation", userInformation);
        }
        //一般形式进入首页
        try {
            List<Activity> activityInformations = service.getAllActivities();
            List<Activity> list = new ArrayList<>();
//            int counts = getShopCounts();
            model.addAttribute("activityInformationCounts", 10);
            for (Activity activityInformation : activityInformations) {
                Activity activity_entity = new Activity();
                activity_entity.setId(activityInformation.getId());
                activity_entity.setTitle(activityInformation.getTitle());
                activity_entity.setStatus(activityInformation.getStatus());
                activity_entity.setEnd_date(activityInformation.getEnd_date());
                activity_entity.setProvide(activityInformation.getProvide());
                activity_entity.setNeed_skill(activityInformation.getNeed_skill());
                activity_entity.setNeed_food(activityInformation.getNeed_food());
                activity_entity.setPic(activityInformation.getPic());
                list.add(activity_entity);
            }
            model.addAttribute("activityInformation", list);
        } catch (Exception e) {
            e.printStackTrace();
            return "page/login_page";
        }
        return "index";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        String token = TokenProccessor.getInstance().makeToken();
//        log.info("进入登录界面，token为:" + token);
        request.getSession().setAttribute("token", token);
        model.addAttribute("token", token);
        return "page/login_page";
    }

    //用户注册,拥有插入数据而已，没什么用的
    @RequestMapping(value = "/registered.do", method = RequestMethod.POST)
    public String registered(Model model,
                             @RequestParam String name, @RequestParam String phone, @RequestParam String password) {
        User_reg userInformation = new User_reg();
        userInformation.setUsername(name);
        userInformation.setPhone(phone);

            model.addAttribute("result", "success");
            return "success";
        }
    }

