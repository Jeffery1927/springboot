package com.chinatown.repository;

import com.chinatown.entity.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


@Transactional
@Repository("ActivityRepository")
//public interface ActivityRepository extends JpaRepository<Activity, String> {
//
//    @Query(value= "SELECT * FROM TBL_ACTIVITIES where phone = ",
//            nativeQuery=true)
//    public List<Activity> getAllActivities();
//}
public interface ActivityRepository extends CrudRepository<Activity, Long> {
}

