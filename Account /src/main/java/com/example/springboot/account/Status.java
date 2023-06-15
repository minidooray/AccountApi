package com.example.springboot.account;

public enum Status {

    Subscription("가입", 10),
    withdrawal("탈퇴", 20),
    dormancy("휴면", 30);

    private final String name;
    private final int value;

    private Status(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

}
