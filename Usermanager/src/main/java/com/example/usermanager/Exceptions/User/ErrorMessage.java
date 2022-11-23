package com.example.usermanager.Exceptions.User;

public enum ErrorMessage {
    CODE_1("Test"),
    CODE_2("Test"),
    CODE_3("TEst");

    private String code;
    private ErrorMessage(String code) {
        this.code = code;
    }

    public  String getCode() {
        return this.code;
    }
}
