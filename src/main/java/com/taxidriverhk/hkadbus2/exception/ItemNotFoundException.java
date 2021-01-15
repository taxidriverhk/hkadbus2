package com.taxidriverhk.hkadbus2.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ItemNotFoundException extends WebApplicationException {

    public ItemNotFoundException(final String identifier) {
        super(new IllegalArgumentException(String.format("Item of identifier %s is not found", identifier)), Response.Status.NOT_FOUND);
    }
}
