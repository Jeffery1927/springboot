package com.chinatown.repository;

import com.chinatown.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@Repository("ActivityRepository2")
public interface ActivityRepository2 extends JpaRepository<Activity, String>{
    @Query(value = "select * from tbl_activities limit 10",nativeQuery = true)
    List<Activity> selectTen(int end);

    @Query(value = "select event_sponsor_id from tbl_activities where id = :id",nativeQuery = true)
    String getSponserIdByActivityID(@Param("id") Long id);
}

