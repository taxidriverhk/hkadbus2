package com.taxidriverhk.hkadbus2.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BadRequestException extends WebApplicationException {

    public BadRequestException(final String message) {
        super(new IllegalArgumentException(message), Response.Status.BAD_REQUEST);
    }
}
