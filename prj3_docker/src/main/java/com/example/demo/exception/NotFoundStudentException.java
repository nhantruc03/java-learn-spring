package com.example.demo.exception;

public class NotFoundStudentException extends RuntimeException {

    public NotFoundStudentException(String msg){
        super(msg);
    }

}
