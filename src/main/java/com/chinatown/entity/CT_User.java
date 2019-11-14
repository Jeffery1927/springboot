package com.chinatown.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * User实体类
 * @author : Qiang
 * @date : 2019-09-30 11:20
 **/

@Entity
@Table(name = "tbl_user")
@GenericGenerator(name = "user_jpa_uuid",strategy = "uuid")
@EntityListeners(AuditingEntityListener.class)
public class CT_User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "user_jpa_uuid")
    private String id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "Email")
    private String Email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "insert_date")
    @CreatedDate
    private Date CreateDate;

    @Column(name = "update_date")
    @LastModifiedDate
    private Date updateDate;

    @Column(name = "status")
    private String status;

    @Column(name = "userImg")
    private String userImg;

    @Column(name = "address")
    private  String address;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getEmail() { return Email; }

    public void setEmail(String email) { Email = email; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", CreateDate=" + CreateDate +
                ", updateDate=" + updateDate +
                ", status='" + status + '\'' +
                ", userImg='" + userImg + '\'' +
                '}';
    }

}
