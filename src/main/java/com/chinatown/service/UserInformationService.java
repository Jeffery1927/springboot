package com.chinatown.service;

import com.chinatown.entity.CT_User;
import com.chinatown.entity.User_reg;

import java.util.List;
import java.util.Optional;

public interface UserInformationService {

    CT_User selectByPrimaryKey(String id);

    CT_User selectByPhone(String phone);

    String selectIdByPhone(String Phone);

    Optional<CT_User> findById(String id);

    List<CT_User> findAll();

    void insertSelective(CT_User userInformation);

    int deleteById(String uid);

    CT_User findByPhone(String phone);

}


