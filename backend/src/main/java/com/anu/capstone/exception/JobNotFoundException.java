package com.anu.capstone.exception;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(String msg){
        super(msg);
    }
}
