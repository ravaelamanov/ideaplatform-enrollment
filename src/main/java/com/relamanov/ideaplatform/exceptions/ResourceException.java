package com.relamanov.ideaplatform.exceptions;

public class ResourceException extends RuntimeException {
    private static final String MSG_FORMAT = "Couldn't access resource. Path: %s.";

    public ResourceException(Throwable cause) {
        super(cause);
    }

    public ResourceException(String path) {
        super(String.format(MSG_FORMAT, path));
    }
}
