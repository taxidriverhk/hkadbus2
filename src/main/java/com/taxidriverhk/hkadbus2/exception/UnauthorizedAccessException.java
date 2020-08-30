package com.taxidriverhk.hkadbus2.exception;

public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(final String message) {
        super(message);
    }
}
