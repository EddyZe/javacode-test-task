package ru.edd.javacodetesttask.exception;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(String msg) {
        super(msg);
    }
}
