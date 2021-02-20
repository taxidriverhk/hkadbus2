package com.taxidriverhk.hkadbus2.provider;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.repository.impl.SqlRepositoryTestBase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SearchPhotoProviderTest extends SqlRepositoryTestBase {

    private SearchPhotoProvider provider;

    @BeforeEach
    public void setup() {
        provider = new SearchPhotoProvider(sessionFactory);
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
            final int expectedSize
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
    }

    public static Stream<Arguments> searchRecordTestCases() {
        return Stream.of(
                arguments(Collections.EMPTY_LIST,
                        "uploadedDate",
                        "asc",
                        SearchPhotoFilter.builder()
                                .uploaderNames(Collections.singletonList("admin"))
                                .build(),
                        null,
                        1,
                        2L,
                        1),
                arguments(Lists.newArrayList("3AV55", "mcdonalds"),
                        "uploadedDate",
                        "asc",
                        SearchPhotoFilter.builder().build(),
                        null,
                        1000,
                        2L,
                        2),
                arguments(Lists.newArrayList("3AV59"),
                        "uploadedDate",
                        "asc",
                        SearchPhotoFilter.builder().build(),
                        null,
                        1000,
                        1L,
                        1),
                arguments(Lists.newArrayList("3AV55", "mcdonalds"),
                        "uploadedDate",
                        "asc",
                        SearchPhotoFilter.builder().build(),
                        "12345",
                        1000,
                        2L,
                        1),
                arguments(Lists.newArrayList("3AV55", "mcdonalds"),
                        "uploadedDate",
                        "desc",
                        SearchPhotoFilter.builder().build(),
                        "12346",
                        1000,
                        2L,
                        1),
                arguments(Collections.EMPTY_LIST,
                        "uploadedDate",
                        "desc",
                        SearchPhotoFilter.builder()
                                .licensePlateNumbers(Lists.newArrayList("gx4965"))
                                .build(),
                        null,
                        1000,
                        1L,
                        1)
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
