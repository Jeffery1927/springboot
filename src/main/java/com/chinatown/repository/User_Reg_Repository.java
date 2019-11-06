package com.chinatown.repository;


import com.chinatown.entity.CT_User;
import com.chinatown.entity.User_reg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface User_Reg_Repository extends JpaRepository<User_reg, String> {

    @Query(value = "select * from tbl_user_reg where phone = :phone ",nativeQuery = true)
    CT_User selectByPhone(@Param("phone") String phone);

    @Query(value = "select * from tbl_user_reg where id = :id",nativeQuery = true)
    CT_User selectByPrimaryKey(@Param("id") String id);

    @Query(value = "select user_information_id from tbl_user_reg where phone = :phone",nativeQuery = true)
    String selectIdByPhone(@Param("phone") String phone);

    @Query(value = "select * from tbl_user_reg where phone = :phone",nativeQuery = true)
    List<CT_User> findByPhone(@Param("phone") String phone);

    @Query(value = "delete from tbl_user_reg where user_information_id = :id",nativeQuery = true)
    int deleteByUserId(@Param("id") String id);


}