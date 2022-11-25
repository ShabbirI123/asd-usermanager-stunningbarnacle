package com.example.usermanager.Exceptions.User;

public class DBInputException extends Exception{

    public DBInputException(String errorMessage, Throwable throwable){
        super(errorMessage, throwable);
    }

}
