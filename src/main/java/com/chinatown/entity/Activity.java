package com.chinatown.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="tbl_activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "event_sponsor_id")
    private String event_sponsor_id;
    @Column(name = "attend_people_id")
    private String attend_people_id;
    @Column(name = "start_date")
    private Date start_date;
    @Column(name = "start_time")
    private Time start_time;
    @Column(name = "status")
    private String status;
    @Column(name = "end_date")
    private Date end_date;
    @Column(name = "provide")
    private String provide;
    @Column(name = "need_food")
    private String need_food;
    @Column(name = "need_skill")
    private String need_skill;
    @Column(name = "address")
    private String address;
    @Column(name = "pic")
    private String pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent_sponsor_id() {
        return event_sponsor_id;
    }

    public void setEvent_sponsor_id(String event_sponsor_id) {
        this.event_sponsor_id = event_sponsor_id;
    }

    public String getAttend_people_id() {
        return attend_people_id;
    }

    public void setAttend_people_id(String attend_people_id) {
        this.attend_people_id = attend_people_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getProvide() {
        return provide;
    }

    public void setProvide(String provide) {
        this.provide = provide;
    }

    public String getNeed_food() {
        return need_food;
    }

    public void setNeed_food(String need_food) {
        this.need_food = need_food;
    }

    public String getNeed_skill() {
        return need_skill;
    }

    public void setNeed_skill(String need_skill) {
        this.need_skill = need_skill;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() { return pic; }

    public void setPic(String pic) { this.pic = pic; }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", event_sponsor_id='" + event_sponsor_id + '\'' +
                ", attend_people_id='" + attend_people_id + '\'' +
                ", start_date=" + start_date +
                ", start_time=" + start_time +
                ", status='" + status + '\'' +
                ", end_date=" + end_date +
                ", provide='" + provide + '\'' +
                ", need_food='" + need_food + '\'' +
                ", need_skill='" + need_skill + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
