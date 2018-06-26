package com.sinothk.rxRetrofitDemo.retrofit.temp;

public class UserEntity {
    private int id;
    private String name;

    public UserEntity() {
    }

    public UserEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
