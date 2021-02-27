package com.example.adminapp.pojo;

public class newsfeed {
    public newsfeed(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public newsfeed() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    String name;
    String desc;
}
