package com.szzrain.doudizhu;

public class GameProgressException extends RuntimeException{
    public GameProgressException() {
        super();
    }

    public GameProgressException(String message) {
        super(message);
    }

    public GameProgressException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameProgressException(Throwable cause) {
        super(cause);
    }
}
