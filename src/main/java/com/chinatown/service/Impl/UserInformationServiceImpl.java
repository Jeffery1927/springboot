package com.chinatown.service.Impl;

import com.chinatown.entity.CT_User;
import com.chinatown.repository.User_Repository;
import com.chinatown.service.UserInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
class UseInformationServiceImpl implements UserInformationService {
    @Resource
    private User_Repository userInfoRepository;

    @Override
    public CT_User selectByPrimaryKey(String id) {

        return userInfoRepository.selectByPrimaryKey(id);
    }

    @Override
    public CT_User selectByPhone(String phone) {

        return userInfoRepository.selectByPhone(phone);
    }

    @Override
    public List<CT_User> findAll() {

        return userInfoRepository.findAll();
    }

    @Override
    public Optional<CT_User> findById(String id) {
        return userInfoRepository.findById(id);
    }

    @Override
    public String selectIdByPhone(String phone) {
        return userInfoRepository.selectIdByPhone(phone);
    }

    @Override
    public int deleteById(String uid) {
        return userInfoRepository.deleteByUserId(uid);
    }

    @Override
    public void insertSelective(CT_User userInformation) {
        userInfoRepository.save(userInformation);
    }

    @Override
    public CT_User findByPhone(String phone) {
        return userInfoRepository.findByPhone(phone);
    }
}