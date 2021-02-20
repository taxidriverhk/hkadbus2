package com.taxidriverhk.hkadbus2.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.model.domain.BusCompany;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.UserEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public final class MockDataHelper {

    public static final String LANGUAGE_EN = "en_us";
    public static final String LANGUAGE_ZH = "zh_hk";

    public static final String BUS_BRAND_HASH_KEY_1 = "dennis";
    public static final String BUS_BRAND_HASH_KEY_2 = "volvo";
    public static final String BUS_MODEL_HASH_KEY_1 = "dragon-12m";
    public static final String BUS_MODEL_HASH_KEY_2 = "olympian-12m";
    public static final String BUS_ROUTE_HASH_KEY_1 = "kmb-49x";

    public static final String ADVERTISEMENT_HASH_KEY_1 = "calbee";
    public static final String ADVERTISEMENT_HASH_KEY_2 = "golden-elephant-brand";
    public static final String ADVERTISEMENT_HASH_KEY_3 = "ikea";

    public static final String CATEGORY_HASH_KEY_1 = "food";
    public static final String CATEGORY_HASH_KEY_2 = "furniture";

    public static final Long TIMESTAMP_2020 = 1577836800000L;
    public static final Timestamp DATE_2020 = Timestamp.from(Instant.ofEpochMilli(TIMESTAMP_2020));

    public static final Category CATEGORY = Category.builder()
            .id(CATEGORY_HASH_KEY_1)
            .name("Food")
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final UUID CATEGORY_ENTITY_ID_1 = createNamedUUID(CATEGORY_HASH_KEY_1);
    public static final CategoryEntity CATEGORY_ENTITY_1 = CategoryEntity.builder()
            .id(CATEGORY_ENTITY_ID_1)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Food",
                    LANGUAGE_ZH, "食品"))
            .hashKey(CATEGORY_HASH_KEY_1)
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final CategoryEntity CATEGORY_ENTITY_2 = CategoryEntity.builder()
            .id(createNamedUUID(CATEGORY_HASH_KEY_2))
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Furniture",
                    LANGUAGE_ZH, "傢俬"))
            .hashKey(CATEGORY_HASH_KEY_2)
            .thumbnail("http://thunbnail.jpg")
            .build();

    public static final Advertisement ADVERTISEMENT_1 = Advertisement.builder()
            .id(ADVERTISEMENT_HASH_KEY_1)
            .categoryId("food")
            .categoryName("Food")
            .name("Calbee")
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final Advertisement ADVERTISEMENT_2 = Advertisement.builder()
            .id("golden-elephant-brand")
            .categoryId("food")
            .categoryName("Food")
            .name("Golden Elephant Brand")
            .thumbnail("http://thunbnail.jpg")
            .build();

    public static final AdvertisementEntity ADVERTISEMENT_ENTITY_1 = AdvertisementEntity.builder()
            .id(createNamedUUID(ADVERTISEMENT_HASH_KEY_1))
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Calbee",
                    LANGUAGE_ZH, "卡樂B"))
            .hashKey(ADVERTISEMENT_HASH_KEY_1)
            .category(CATEGORY_ENTITY_1)
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final AdvertisementEntity ADVERTISEMENT_ENTITY_2 = AdvertisementEntity.builder()
            .id(createNamedUUID(ADVERTISEMENT_HASH_KEY_2))
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Golden Elephant Brand",
                    LANGUAGE_ZH, "金象米"))
            .hashKey(ADVERTISEMENT_HASH_KEY_2)
            .category(CATEGORY_ENTITY_1)
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final AdvertisementEntity ADVERTISEMENT_ENTITY_3 = AdvertisementEntity.builder()
            .id(createNamedUUID(ADVERTISEMENT_HASH_KEY_3))
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "IKEA",
                    LANGUAGE_ZH, "宜家傢俬"))
            .category(CATEGORY_ENTITY_2)
            .hashKey(ADVERTISEMENT_HASH_KEY_3)
            .thumbnail("http://thunbnail.jpg")
            .build();

    public static final BusModel BUS_MODEL_1 = BusModel.builder()
            .id(BUS_MODEL_HASH_KEY_1)
            .name("Dragon 12M")
            .thumbnail("http://thunbnail.jpg")
            .busBrandId(BUS_BRAND_HASH_KEY_1)
            .busBrandName("Dennis")
            .build();
    public static final BusModel BUS_MODEL_2 = BusModel.builder()
            .id(BUS_MODEL_HASH_KEY_2)
            .name("Olympian 12M")
            .thumbnail("http://thunbnail.jpg")
            .busBrandId(BUS_BRAND_HASH_KEY_2)
            .busBrandName("Volvo")
            .build();

    public static final BusBrandEntity BUS_BRAND_ENTITY_1 = BusBrandEntity.builder()
            .id(createNamedUUID(BUS_BRAND_HASH_KEY_1))
            .hashKey(BUS_BRAND_HASH_KEY_1)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Dennis",
                    LANGUAGE_ZH, "丹尼士"))
            .build();
    public static final BusBrandEntity BUS_BRAND_ENTITY_2 = BusBrandEntity.builder()
            .id(createNamedUUID(BUS_BRAND_HASH_KEY_2))
            .hashKey(BUS_BRAND_HASH_KEY_2)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Volvo",
                    LANGUAGE_ZH, "富豪"))
            .build();

    public static final BusModelEntity BUS_MODEL_ENTITY_1 = BusModelEntity.builder()
            .id(createNamedUUID(BUS_BRAND_HASH_KEY_1))
            .hashKey(BUS_MODEL_HASH_KEY_1)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Dragon 12M",
                    LANGUAGE_ZH, "巨龍12米"))
            .thumbnail("http://thunbnail.jpg")
            .busBrand(BUS_BRAND_ENTITY_1)
            .build();
    public static final BusModelEntity BUS_MODEL_ENTITY_2 = BusModelEntity.builder()
            .id(createNamedUUID(BUS_BRAND_HASH_KEY_2))
            .hashKey(BUS_MODEL_HASH_KEY_2)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Olympian 12M",
                    LANGUAGE_ZH, "奧林比安12米"))
            .thumbnail("http://thunbnail.jpg")
            .busBrand(BUS_BRAND_ENTITY_2)
            .build();

    public static final String BUS_ID_1 = "ASDgTMT2mu";
    public static final BusEntity BUS_ENTITY_1 = BusEntity.builder()
            .id(createNamedUUID("AD15"))
            .shortId(1L)
            .busCompany(BusCompany.KMB.getText())
            .fleetPrefix("AD")
            .fleetNumber("15")
            .licensePlateNumber("EA5913")
            .busModel(BUS_MODEL_ENTITY_1)
            .build();

    public static final String BUS_ID_2 = "n3h5TIFj4v";
    public static final BusEntity BUS_ENTITY_2 = BusEntity.builder()
            .id(createNamedUUID("3AV101"))
            .shortId(2L)
            .busCompany(BusCompany.KMB.getText())
            .fleetPrefix("3AV")
            .fleetNumber("101")
            .licensePlateNumber("GX2424")
            .busModel(BUS_MODEL_ENTITY_2)
            .build();

    public static final BusRouteEntity BUS_ROUTE_ENTITY_1 = BusRouteEntity.builder()
            .routeNumber("49X")
            .hashKey("kmb-49x")
            .busCompanies(Lists.newArrayList(BusCompany.KMB.getText()))
            .startLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Kwong Yuen",
                    LANGUAGE_ZH, "廣源"))
            .endLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Tsing Yi Ferry Pier",
                    LANGUAGE_ZH, "青衣碼頭"))
            .build();
    public static final BusRouteEntity BUS_ROUTE_ENTITY_2 = BusRouteEntity.builder()
            .routeNumber("2")
            .hashKey("kmb-2")
            .busCompanies(Lists.newArrayList(BusCompany.KMB.getText()))
            .startLocation(ImmutableMap.of(
                    LANGUAGE_EN, "So Uk",
                    LANGUAGE_ZH, "蘇屋"))
            .endLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Star Ferry",
                    LANGUAGE_ZH, "尖沙咀碼頭"))
            .build();
    public static final BusRouteEntity BUS_ROUTE_ENTITY_3 = BusRouteEntity.builder()
            .routeNumber("2")
            .hashKey("nwfb-2")
            .busCompanies(Lists.newArrayList(BusCompany.NWFB.getText()))
            .startLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Central (Macau Ferry)",
                    LANGUAGE_ZH, "中環 (港澳碼頭)"))
            .endLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Grand Promenade",
                    LANGUAGE_ZH, "嘉亨灣"))
            .build();

    public static final UserEntity USER_ENTITY_1 = UserEntity.builder()
            .id(createNamedUUID("admin"))
            .username("test-user")
            .passwordHash("test-hash")
            .group("admin")
            .registrationDate(DATE_2020)
            .lastLoggedInDate(DATE_2020)
            .build();

    public static final Long PHOTO_SHORT_ID_1 = 12345L;

    public static final Photo PHOTO_1 = Photo.builder()
            .photoId(PHOTO_SHORT_ID_1)
            .advertisementId(ADVERTISEMENT_HASH_KEY_1)
            .advertisement("Calbee")
            .categoryId(CATEGORY_HASH_KEY_1)
            .category("Food")
            .busCompany(BusCompany.KMB)
            .busId(1L)
            .busModelId(BUS_MODEL_HASH_KEY_1)
            .busModel("Dragon 12M")
            .licensePlateNumber("EA5913")
            .fleetPrefix("AD")
            .fleetNumber("15")
            .routeNumber("49X")
            .image("http://image.jpg")
            .thumbnail("http://thunbnail.jpg")
            .username("test-user")
            .uploadedDate(TIMESTAMP_2020)
            .build();

    public static final PhotoEntity PHOTO_ENTITY_1 = PhotoEntity.builder()
            .id(createNamedUUID(PHOTO_SHORT_ID_1.toString()))
            .shortId(PHOTO_SHORT_ID_1)
            .image("http://image.jpg")
            .thumbnail("http://thunbnail.jpg")
            .advertisement(ADVERTISEMENT_ENTITY_1)
            .bus(BUS_ENTITY_1)
            .busRoute(BUS_ROUTE_ENTITY_1)
            .user(USER_ENTITY_1)
            .uploadedDate(DATE_2020)
            .build();

    public static final SearchRecord SEARCH_RECORD_1 = SearchRecord.builder()
            .photoId(1L)
            .advertisementId("mcdonalds")
            .advertisement("McDonald's")
            .categoryId("restaurant")
            .category("Restaurant")
            .busCompany(BusCompany.KMB)
            .busModelId("volvo-olympian-12m")
            .busModel("Volvo Olympian 12M")
            .routeId("kmb-215x")
            .routeNumber("215X")
            .licensePlateNumber("GX4965")
            .fleetPrefix("3AV")
            .fleetNumber("55")
            .thumbnail("http://thumbnail.jpg")
            .username("admin")
            .uploadedDate(12345L)
            .tags(Lists.newArrayList("mcdonalds", "3av55", "gx4965"))
            .build();

    public static final SearchRecordEntity SEARCH_RECORD_ENTITY_1 = SearchRecordEntity.builder()
            .photoShortId(1L)
            .advertisementHashKey("mcdonalds")
            .advertisementName("McDonald's")
            .categoryHashKey("restaurant")
            .categoryName("Restaurant")
            .busCompany("kmb")
            .busModelHashKey("volvo-olympian-12m")
            .busModelName("Volvo Olympian 12M")
            .routeHashKey("kmb-215x")
            .routeNumber("215X")
            .licensePlateNumber("GX4965")
            .fleetPrefix("3AV")
            .fleetNumber("55")
            .thumbnail("http://thumbnail.jpg")
            .username("admin")
            .uploadedDate(12345L)
            .tags("mcdonalds,3av55,gx4965")
            .language(LANGUAGE_EN)
            .build();
    public static final SearchRecordEntity SEARCH_RECORD_ENTITY_2 = SearchRecordEntity.builder()
            .photoShortId(2L)
            .advertisementHashKey("mcdonalds")
            .advertisementName("McDonald's")
            .categoryHashKey("restaurant")
            .categoryName("Restaurant")
            .busCompany("kmb")
            .busModelHashKey("volvo-olympian-12m")
            .busModelName("Volvo Olympian 12M")
            .routeHashKey("kmb-215x")
            .routeNumber("215X")
            .licensePlateNumber("GX4966")
            .fleetPrefix("3AV")
            .fleetNumber("59")
            .thumbnail("http://thumbnail.jpg")
            .username("admin")
            .uploadedDate(12346L)
            .tags("mcdonalds,3av59,gx4966")
            .language(LANGUAGE_EN)
            .build();

    private static UUID createNamedUUID(final String name) {
        return UUID.nameUUIDFromBytes(name.getBytes());
    }
}
