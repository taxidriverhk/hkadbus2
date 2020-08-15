package com.taxidriverhk.hkadbus2.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(final String identifier) {
        super(String.format("Item of identifier %s is not found", identifier));
    }
}
