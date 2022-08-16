package com.exeception;

public class ExecuteSeckillException extends RuntimeException{

    public ExecuteSeckillException(String message) {
        super(message);
    }

    public ExecuteSeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
