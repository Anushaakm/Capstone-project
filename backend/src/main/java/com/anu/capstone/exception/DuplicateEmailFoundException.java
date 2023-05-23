package com.anu.capstone.exception;

public class DuplicateEmailFoundException extends RuntimeException {
    public DuplicateEmailFoundException(String msg) {
        super(msg);
    }
}