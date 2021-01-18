package com.taxidriverhk.hkadbus2.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.model.domain.BusCompany;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;

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

    public static final String BUS_ID_1 = "ASDgTMT2mu";
    public static final BusEntity BUS_ENTITY_1 = BusEntity.builder()
            .busId(BUS_ID_1)
            .busModelId(BUS_MODEL_HASH_KEY_1)
            .busCompany(BusCompany.KMB.getText())
            .fleetPrefix("AD")
            .fleetNumber(15)
            .licensePlateNumber("EA5913")
            .build();

    public static final String BUS_ID_2 = "n3h5TIFj4v";
    public static final BusEntity BUS_ENTITY_2 = BusEntity.builder()
            .busId(BUS_ID_2)
            .busModelId(BUS_MODEL_HASH_KEY_2)
            .busCompany(BusCompany.KMB.getText())
            .fleetPrefix("3AV")
            .fleetNumber(101)
            .licensePlateNumber("GX2424")
            .build();

    public static final BusBrandEntity BUS_BRAND_ENTITY_1 = BusBrandEntity.builder()
            .hashKey(BUS_BRAND_HASH_KEY_1)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Dennis",
                    LANGUAGE_ZH, "丹尼士"))
            .build();
    public static final BusBrandEntity BUS_BRAND_ENTITY_2 = BusBrandEntity.builder()
            .hashKey(BUS_BRAND_HASH_KEY_2)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Volvo",
                    LANGUAGE_ZH, "富豪"))
            .build();

    public static final BusModelEntity BUS_MODEL_ENTITY_1 = BusModelEntity.builder()
            .hashKey(BUS_MODEL_HASH_KEY_1)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Dragon 12M",
                    LANGUAGE_ZH, "巨龍12米"))
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final BusModelEntity BUS_MODEL_ENTITY_2 = BusModelEntity.builder()
            .hashKey(BUS_MODEL_HASH_KEY_2)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Olympian 12M",
                    LANGUAGE_ZH, "奧林比安12米"))
            .thumbnail("http://thunbnail.jpg")
            .build();

    public static final BusRouteEntity BUS_ROUTE_ENTITY_1 = BusRouteEntity.builder()
            .hashKey(BUS_ROUTE_HASH_KEY_1)
            .routeNumber("49X")
            .busCompanies(Lists.newArrayList(BusCompany.KMB.getText()))
            .startLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Kwong Yuen",
                    LANGUAGE_ZH, "廣源"))
            .endLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Tsing Yi Ferry Pier",
                    LANGUAGE_ZH, "青衣碼頭"))
            .build();
    public static final BusRouteEntity BUS_ROUTE_ENTITY_2 = BusRouteEntity.builder()
            .hashKey(BUS_ROUTE_HASH_KEY_1)
            .routeNumber("2")
            .busCompanies(Lists.newArrayList(BusCompany.KMB.getText()))
            .startLocation(ImmutableMap.of(
                    LANGUAGE_EN, "So Uk",
                    LANGUAGE_ZH, "廣源"))
            .endLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Star Ferry",
                    LANGUAGE_ZH, "青衣碼頭"))
            .build();
    public static final BusRouteEntity BUS_ROUTE_ENTITY_3 = BusRouteEntity.builder()
            .hashKey(BUS_ROUTE_HASH_KEY_1)
            .routeNumber("2")
            .busCompanies(Lists.newArrayList(BusCompany.NWFB.getText()))
            .startLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Central (Macau Ferry)",
                    LANGUAGE_ZH, "中環 (港澳碼頭)"))
            .endLocation(ImmutableMap.of(
                    LANGUAGE_EN, "Grand Promenade",
                    LANGUAGE_ZH, "嘉亨灣"))
            .build();

    public static final String PHOTO_ID_1 = "a2Lbjw3eNS";

    public static final Photo PHOTO_1 = Photo.builder()
            .photoId(PHOTO_ID_1)
            .advertisementId(ADVERTISEMENT_HASH_KEY_1)
            .advertisement("Calbee")
            .busCompany(BusCompany.KMB)
            .busModelId(BUS_MODEL_HASH_KEY_1)
            .busModel("Dragon 12M")
            .busRouteId(BUS_ROUTE_HASH_KEY_1)
            .busRoute("49X")
            .licensePlateNumber("EA5913")
            .fleetPrefix("AD")
            .fleetNumber(15)
            .image("http://image.jpg")
            .thumbnail("http://thunbnail.jpg")
            .username("test-user")
            .uploadedDate(123456L)
            .build();

    public static final PhotoEntity PHOTO_ENTITY_1 = PhotoEntity.builder()
            .photoId(PHOTO_ID_1)
            .advertisementId(ADVERTISEMENT_HASH_KEY_1)
            .busId(BUS_ID_1)
            .busRouteId(BUS_ROUTE_HASH_KEY_1)
            .image("http://image.jpg")
            .thumbnail("http://thunbnail.jpg")
            .userId("test-user")
            .uploadedDate(123456L)
            .build();

    private static UUID createNamedUUID(final String name) {
        return UUID.nameUUIDFromBytes(name.getBytes());
    }
}
