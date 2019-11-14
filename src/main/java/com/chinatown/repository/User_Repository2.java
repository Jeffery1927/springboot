package com.chinatown.repository;

import com.chinatown.entity.Activity;
import com.chinatown.entity.CT_User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
@Repository("User_Repository2")
public interface User_Repository2 extends CrudRepository<CT_User, Long> {
}
