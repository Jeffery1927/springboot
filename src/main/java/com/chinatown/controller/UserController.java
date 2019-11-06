package com.chinatown.controller;

import com.chinatown.entity.CT_User;
import com.chinatown.entity.User_reg;
import com.chinatown.service.UserInformationService;
import com.chinatown.tool.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class UserController {

    @Resource
    private UserInformationService userInformationService;


    //@ResponseBody
    @GetMapping("/Users")
    public List<CT_User> retrieveAllStudents() {
        return userInformationService.findAll();
    }

    @GetMapping(value = "/user/id/{id}")
    public CT_User findById(@PathVariable String id) {
        Optional<CT_User> userInformation = userInformationService.findById(id);
        return userInformation.get();
    }

    @GetMapping(value = "/user/phone/{phone}")
    public CT_User selectByPhone(@PathVariable String phone) {
        CT_User userInformation2 = userInformationService.selectByPhone(phone);
        return userInformation2;
    }


    @GetMapping(value = "/user_id/phone/{phone}")
    public String selectIdByPhone(@PathVariable String phone) {
        String user_id = userInformationService.selectIdByPhone(phone);
        return user_id;
    }

    @RequestMapping(value = "chinatown/personal_info.do")
    public String personalInfo(HttpServletRequest request, Model model) {
        CT_User userInformation = (CT_User) request.getSession().getAttribute("userInformation");

        if (StringUtils.getInstance().isNullOrEmpty(userInformation)) {
            return "redirect:/login.do";
        }
//        String personalInfoToken = TokenProccessor.getInstance().makeToken();
//        request.getSession().setAttribute("personalInfoToken", personalInfoToken);
//        model.addAttribute("token", personalInfoToken);
            model.addAttribute("userInformation", userInformation);
            return "page/personal/personal_info";
        }
    }
