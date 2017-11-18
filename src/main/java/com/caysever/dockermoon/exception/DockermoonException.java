package com.caysever.dockermoon.exception;

public class DockermoonException extends RuntimeException {

    public DockermoonException(String message) {
        super(message);
    }

    public DockermoonException(Throwable cause) {
        super(cause);
    }

    public DockermoonException(String message, Throwable cause) {
        super(message, cause);
    }
}
