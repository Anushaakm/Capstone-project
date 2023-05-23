package com.anu.capstone.exception;

public class InvalidApplicationException extends RuntimeException{
    public InvalidApplicationException(String msg){
        super(msg);
    }
}