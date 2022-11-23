package com.example.usermanager.Exceptions.User;

public class DBInputException extends Exception{

    public DBInputException(String errorMessage, String errorMessageCode, Throwable throwable){
        super(DBInputException.getExceptionText(errorMessage, errorMessageCode), throwable);
    }

    public static String getExceptionText(String errorMessage, String errorMessageCode) {
        if (errorMessage == null || errorMessage == ""){
            for (ErrorMessage message : ErrorMessage.values()){
                if (ErrorMessage.valueOf(errorMessageCode) == message){
                    return message.getCode();
                }
            }
        } else {
            return errorMessage;
        }
        return "";
    }
}
