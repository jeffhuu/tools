package com.jeff.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: demo
 * @description:
 * @author: Jeff Hu 2022/06/16 11:15
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("name");
        list.add(user);

        System.out.println(list);
        List<User> collect = list.stream().peek(u -> u.setName("Jack")).collect(Collectors.toList());
        System.out.println(collect);

    }
}

class User {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}