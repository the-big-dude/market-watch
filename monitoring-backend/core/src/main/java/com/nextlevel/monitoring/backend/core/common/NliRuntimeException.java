package com.nextlevel.monitoring.backend.core.common;

public class NliRuntimeException extends Throwable {
    public NliRuntimeException(String s, Exception e) {
        super(s, e);
    }

    public NliRuntimeException(String s) {
        super(s);
    }

    public NliRuntimeException(Exception e) {
        super(e);
    }
}
