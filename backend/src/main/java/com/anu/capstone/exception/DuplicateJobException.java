package com.anu.capstone.exception;

public class DuplicateJobException extends RuntimeException{
    public DuplicateJobException(String msg){
        super(msg);
    }
}
