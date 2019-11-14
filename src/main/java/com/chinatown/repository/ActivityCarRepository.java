package com.chinatown.repository;

import com.chinatown.entity.Activity;
import com.chinatown.entity.Activity_Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("ActivityCarRepository")
public interface ActivityCarRepository extends JpaRepository<Activity_Car, String> {
    @Query(value = "select * from tbl_activity_car where attend_people_id = :id",nativeQuery = true)
    List<Activity_Car> getActivitiesCar(@Param("id") String id);
    @Query(value = "select * from tbl_activity_car where id = :id",nativeQuery = true)
    Activity_Car getActivitiesById(@Param("id") Long id);
}

