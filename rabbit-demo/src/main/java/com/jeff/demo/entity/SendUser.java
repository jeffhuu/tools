package com.jeff.demo.entity;

import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: Jeff Hu 2022/06/14 16:30
 */
public class SendUser implements Serializable {


    private Integer id;

    private String name;

    private String phone;


    public SendUser() {
    }

    public SendUser(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "sendUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
