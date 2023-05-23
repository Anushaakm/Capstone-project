package com.anu.capstone.exception;

public class InvalidRoleException extends RuntimeException{
    public InvalidRoleException(String msg){
        super(msg);
    }
}