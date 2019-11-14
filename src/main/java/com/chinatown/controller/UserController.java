package com.chinatown.controller;

import com.chinatown.entity.CT_User;
import com.chinatown.entity.User_reg;
import com.chinatown.repository.User_Repository2;
import com.chinatown.service.UserInformationService;
import com.chinatown.tool.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
public class UserController {

    @Resource
    private UserInformationService userInformationService;
    @Resource
    private User_Repository2 userRepository2;


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

    //完善用户基本信息，认证
    @RequestMapping(value = "/certification.do", method = RequestMethod.POST)
    @ResponseBody
    public Map certification(HttpServletRequest request,
                             @RequestParam(required = false) String userName,
                             @RequestParam(required = false) String realName,
                             @RequestParam(required = false) String address,
                             @RequestParam(required = false) String gender,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) String email){
        CT_User userInformation = (CT_User) request.getSession().getAttribute("userInformation");
        Map<String, Integer> map = new HashMap<>();
        map.put("result", 0);
        //该用户还没有登录
        if (StringUtils.getInstance().isNullOrEmpty(userInformation)) {
            return map;
        }

        if (userName != null && userName.length() < 25) {
            userName = StringUtils.getInstance().replaceBlank(userName);
            userInformation.setUsername(userName);
        } else if (userName != null && userName.length() >= 25) {
            return map;
        }
        if (realName != null && realName.length() < 25) {
            realName = StringUtils.getInstance().replaceBlank(realName);
            userInformation.setPhone(realName);
        } else if (realName != null && realName.length() >= 25) {
            return map;
        }
        if (address != null && address.length() < 25) {

            userInformation.setAddress(address);
        } else if (address != null && address.length() >= 25) {
            return map;
        }
        if (gender != null && gender.length() <= 25) {
            gender = StringUtils.getInstance().replaceBlank(gender);
            userInformation.setGender(gender);
        } else if (gender != null && gender.length() > 6) {
            return map;
        }
        if (phone != null && phone.length() <= 25) {
            phone = StringUtils.getInstance().replaceBlank(phone);
            userInformation.setPhone(phone);
        } else if (phone != null && phone.length() > 25) {
            return map;
        }
        if (email != null && email.length() <= 25) {
            email = StringUtils.getInstance().replaceBlank(email);
            userInformation.setGender(email);
        } else if (email != null && email.length() > 2) {
            return map;
        }
        CT_User result = userRepository2.save(userInformation);
        if (result.getId() == null) {
            //更新失败，认证失败
            return map;
        }
        //认证成功
        request.getSession().setAttribute("userInformation", userInformation);
        map.put("result", 1);
        return map;
    }
    }
