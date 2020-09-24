package com.mycompany.capp.exception;

public class UserBLockedException extends Exception {

    public UserBLockedException() {

    }

    public UserBLockedException(String errDesc) {
        super(errDesc);
    }
}
