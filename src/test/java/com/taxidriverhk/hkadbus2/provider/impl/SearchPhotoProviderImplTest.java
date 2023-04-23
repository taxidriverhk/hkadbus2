package com.taxidriverhk.hkadbus2.provider.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.model.domain.BusCompany;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.repository.impl.SqlRepositoryTestBase;

public class SearchPhotoProviderImplTest extends SqlRepositoryTestBase {

    private SearchPhotoProviderImpl provider;

    @BeforeEach
    public void setup() {
        provider = new SearchPhotoProviderImpl(sessionFactory);
    }

    @ParameterizedTest
    @MethodSource("searchRecordTestCases")
    public void GIVEN_searchQueryAndFilter_WHEN_searchForRecords_THEN_shouldReturnMatchingRecords(
            final List<String> queryTexts,
            final String orderBy,
            final String sort,
            final SearchPhotoFilter filter,
            final String nextSortKey,
            final int limit,
            final long expectedTotal,
            final int expectedSize,
            final String expectedNextPageCursor
    ) {
        final SearchRecordResult searchRecordResult = provider.searchPhotos(
                queryTexts,
                orderBy,
                sort,
                filter,
                nextSortKey,
                limit);
        assertThat(searchRecordResult.getTotal(), equalTo(expectedTotal));
        assertThat(searchRecordResult.getSearchRecordEntities(), hasSize(expectedSize));
        assertThat(searchRecordResult.getNextPageCursor(), equalTo(expectedNextPageCursor));
    }

    @Test
    public void GIVEN_invalidNextPageCursor_WHEN_searchForRecords_THEN_shouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> provider.searchPhotos(
                Collections.emptyList(),
                "uploadedDate",
                "asc",
                SearchPhotoFilter.builder()
                        .language("en_us")
                        .build(),
                "invalid-next-cursor",
                1000));
    }

    @Test
    public void GIVEN_searchRecord_WHEN_insert_THEN_shouldBeFoundWithQuery() {
        final SearchRecord searchRecord =  SearchRecord.builder()
                .photoId(8L)
                .advertisementId("sprite")
                .advertisement("Sprite")
                .categoryId("drink")
                .category("Drink")
                .busCompany(BusCompany.KMB)
                .busModelId("volvo-olympian-12m")
                .busModel("Volvo Olympian 12M")
                .routeId("kmb-215x")
                .routeNumber("215X")
                .licensePlateNumber("GX8888")
                .fleetPrefix("3AV")
                .fleetNumber("88")
                .thumbnail("http://thumbnail.jpg")
                .username("admin")
                .uploadedDate(12345L)
                .tags(Lists.newArrayList("mcdonalds", "3av55", "gx4965"))
                .build();
        provider.insertSearchRecord(searchRecord, LANGUAGE_EN);

        final SearchRecordResult searchRecordResult = provider.searchPhotos(
                Collections.emptyList(),
                "uploadedDate",
                "asc",
                SearchPhotoFilter.builder()
                        .licensePlateNumbers(Lists.newArrayList("gx8888"))
                        .build(),
                null,
                1000);
        assertThat(searchRecordResult.getTotal(), equalTo(1L));
    }

    public static Stream<Arguments> searchRecordTestCases() {
        return Stream.of(
                arguments(Collections.emptyList(),
                        "uploadedDate",
                        "asc",
                        SearchPhotoFilter.builder()
                                .uploaderNames(Collections.singletonList("admin"))
                                .build(),
                        null,
                        1,
                        2L,
                        1,
                        "12345-1"),
                arguments(Lists.newArrayList("3AV55", "mcdonalds"),
                        "uploadedDate",
                        "asc",
                        SearchPhotoFilter.builder().build(),
                        null,
                        1000,
                        2L,
                        2,
                        null),
                arguments(Lists.newArrayList("3AV59"),
                        "uploadedDate",
                        "asc",
                        SearchPhotoFilter.builder().build(),
                        null,
                        1000,
                        1L,
                        1,
                        null),
                arguments(Lists.newArrayList("3AV55", "mcdonalds"),
                        "uploadedDate",
                        "asc",
                        SearchPhotoFilter.builder().build(),
                        "12345-1",
                        1000,
                        2L,
                        1,
                        "12346-2"),
                arguments(Lists.newArrayList("3AV55", "mcdonalds"),
                        "uploadedDate",
                        "desc",
                        SearchPhotoFilter.builder().build(),
                        "12346-2",
                        1000,
                        2L,
                        1,
                        "12345-1"),
                arguments(Collections.emptyList(),
                        "uploadedDate",
                        "desc",
                        SearchPhotoFilter.builder()
                                .licensePlateNumbers(Lists.newArrayList("gx4965"))
                                .build(),
                        null,
                        1000,
                        1L,
                        1,
                        null),
                arguments(Collections.emptyList(),
                        "uploadedDate",
                        "desc",
                        SearchPhotoFilter.builder()
                                .thumbnails(Lists.newArrayList("http://thumbnail.jpg"))
                                .build(),
                        null,
                        1000,
                        2L,
                        2,
                        null)
        );
    }

    @Override
    protected void setupDataForTest(final Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(SEARCH_RECORD_ENTITY_1);
        session.save(SEARCH_RECORD_ENTITY_2);
        transaction.commit();
    }
}
