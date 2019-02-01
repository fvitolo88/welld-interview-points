package com.welld.points.exceptions;

public class IllegalPointArgumentException extends IllegalArgumentException {

    private String msg;

    public IllegalPointArgumentException(String msg){
        super(msg);
    }

}
