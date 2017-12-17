package com.caysever.dockermoon.handler;

import com.caysever.dockermoon.exception.DockermoonException;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

@Service
public class ExceptionHandler {

    public static <T> T handle(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DockermoonException(e);
        }
    }

}
