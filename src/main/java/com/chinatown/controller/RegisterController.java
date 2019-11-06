package com.chinatown.controller;

import com.chinatown.entity.CT_User;
import com.chinatown.tool.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.chinatown.service.UserInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/chinatown")
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    @Resource
    private UserInformationService userInformationService;

    //开始注册用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String insertNewUser( @RequestBody CT_User new_CT_User) {
        userInformationService.insertSelective(new_CT_User);
        return "1";
    }

    //登录
    @ResponseBody
    @RequestMapping(value = "/User_login",method = RequestMethod.POST)
    public String login(@RequestBody CT_User loginCTUser, Model model, HttpServletRequest request) {
        CT_User user = userInformationService.findByPhone(loginCTUser.getPhone());
        model.addAttribute("userInformation", user);
        // 如果数据库中未查到该账号:
        if (user == null) {
            log.warn("attempting to log in with the non-existed account");
            return "3";
        } else {
            if (user.getPassword().equals(loginCTUser.getPassword())) {
                request.getSession().setAttribute("userInformation",user);
                // 如果密码与邮箱配对成功:
                log.warn(user.toString() + " logged in");
               return "1";
            } else {
                // 如果密码与邮箱不匹配:
                return "2";
            }
        }
    }
    @RequestMapping(path = "/logout.do")
    public String logout(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute("userInformation");
            request.getSession().removeAttribute("uid");
            System.out.println("logout");
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
        return "redirect:/";
    }

    //注册
    @ResponseBody
    @RequestMapping(value = "/checkCode.do", method = {RequestMethod.POST, RequestMethod.GET})
    public Map checkPhone(HttpServletRequest request, Model model,
                          @RequestParam String code, @RequestParam String token) {
        Map<String, Integer> map = new HashMap<>();
        String name = request.getParameter("name");
        if (!StringUtils.getInstance().isNullOrEmpty(name)) {
            request.getSession().setAttribute("name", name);
        }
        String checkCodeToken = (String) request.getSession().getAttribute("token");
        if (StringUtils.getInstance().isNullOrEmpty(checkCodeToken) || !checkCodeToken.equals(token)) {
            map.put("result", 0);
            return map;
        }
        //验证码错误
        if (!checkCodePhone(code, request)) {
            map.put("result", 0);
            return map;
        }
        map.put("result", 1);
        return map;
    }




    //check the phone`s code
    private boolean checkCodePhone(String codePhone, HttpServletRequest request) {
        String trueCodePhone = "12251103";
        return codePhone.equals(trueCodePhone);
    }
}