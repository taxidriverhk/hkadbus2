package com.taxidriverhk.hkadbus2.model.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityConstants {

    public static final String ADVERTISEMENT_TABLE = "advertisement";
    public static final String BUS_TABLE = "bus";
    public static final String BUS_BRAND_TABLE = "bus_brand";
    public static final String BUS_MODEL_TABLE = "bus_model";
    public static final String BUS_ROUTE_TABLE = "bus_route";
    public static final String CATEGORY_TABLE = "category";
    public static final String PHOTO_TABLE = "photo";
    public static final String SEARCH_RECORD_TABLE = "search_record";
    public static final String USER_TABLE = "user";

    public static final String ID_ATTRIBUTE = "id";
    public static final String SHORT_ID_ATTRIBUTE = "shortId";
    public static final String HASH_KEY_ATTRIBUTE = "hashKey";
}
