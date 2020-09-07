package com.taxidriverhk.hkadbus2.util;

import com.google.common.collect.ImmutableMap;
import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;

public final class MockDataHelper {

    public static final String LANGUAGE_EN = "en_us";
    public static final String LANGUAGE_ZH = "zh_hk";

    public static final String BUS_BRAND_HASH_KEY_1 = "dennis";
    public static final String BUS_BRAND_HASH_KEY_2 = "volvo";
    public static final String BUS_MODEL_HASH_KEY_1 = "dragon-12m";
    public static final String BUS_MODEL_HASH_KEY_2 = "olympian-12m";
    public static final String CATEGORY_HASH_KEY_1 = "food";

    public static final Category CATEGORY = Category.builder()
            .id(CATEGORY_HASH_KEY_1)
            .name("Food")
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final CategoryEntity CATEGORY_ENTITY = CategoryEntity.builder()
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Food",
                    LANGUAGE_ZH, "食品"))
            .hashKey(CATEGORY_HASH_KEY_1)
            .thumbnail("http://thunbnail.jpg")
            .build();

    public static final Advertisement ADVERTISEMENT_1 = Advertisement.builder()
            .id("calbee")
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
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Calbee",
                    LANGUAGE_ZH, "卡樂B"))
            .hashKey("calbee")
            .categoryId(CATEGORY_HASH_KEY_1)
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final AdvertisementEntity ADVERTISEMENT_ENTITY_2 = AdvertisementEntity.builder()
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Golden Elephant Brand",
                    LANGUAGE_ZH, "金象米"))
            .hashKey("golden-elephant-brand")
            .categoryId(CATEGORY_HASH_KEY_1)
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final AdvertisementEntity ADVERTISEMENT_ENTITY_3 = AdvertisementEntity.builder()
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "IKEA",
                    LANGUAGE_ZH, "宜家傢俬"))
            .hashKey("ikea")
            .categoryId("furniture")
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
            .busBrandId(BUS_BRAND_HASH_KEY_1)
            .thumbnail("http://thunbnail.jpg")
            .build();
    public static final BusModelEntity BUS_MODEL_ENTITY_2 = BusModelEntity.builder()
            .hashKey(BUS_MODEL_HASH_KEY_2)
            .name(ImmutableMap.of(
                    LANGUAGE_EN, "Olympian 12M",
                    LANGUAGE_ZH, "奧林比安12米f"))
            .busBrandId(BUS_BRAND_HASH_KEY_2)
            .thumbnail("http://thunbnail.jpg")
            .build();

    public static final String PHOTO_ID_1 = "a2Lbjw3eNS";
    public static final PhotoEntity PHOTO_ENTITY_1 = PhotoEntity.builder()
            .photoId(PHOTO_ID_1)
            .busId("fa-2536")
            .busRouteId("kmb-89x")
            .image("http://image.jpg")
            .thumbnail("http://thunbnail.jpg")
            .userId("test-user")
            .uploadedDate(123456L)
            .build();
}
