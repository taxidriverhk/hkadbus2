package com.taxidriverhk.hkadbus2.provider;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.repository.impl.SqlRepositoryTestBase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class SearchPhotoProviderTest extends SqlRepositoryTestBase {

    private SearchPhotoProvider provider;

    @BeforeEach
    public void setup() {
        provider = new SearchPhotoProvider(sessionFactory);
    }

    @Test
    public void GIVEN_usernameSearchFilter_WHEN_searchForRecords_THEN_shouldReturnMatchingRecords() {
        final SearchRecordResult searchRecordResult = provider.searchPhotos(
                null,
                "uploadedDate",
                "asc",
                SearchPhotoFilter.builder()
                        .uploaderNames(Collections.singletonList("admin"))
                        .build(),
                null,
                1000
        );
        assertThat(searchRecordResult.getSearchRecordEntities(), hasSize(1));
    }

    @Test
    public void GIVEN_invalidUsernameSearchFilter_WHEN_searchForRecords_THEN_shouldReturnNothing() {
        final SearchRecordResult searchRecordResult = provider.searchPhotos(
                null,
                "uploadedDate",
                "asc",
                SearchPhotoFilter.builder()
                        .uploaderNames(Collections.singletonList("random-name"))
                        .build(),
                null,
                1000
        );
        assertThat(searchRecordResult.getSearchRecordEntities(), hasSize(0));
    }

    @Override
    protected void setupDataForTest(final Session session) {
        final Transaction transaction = session.beginTransaction();

        final SearchRecordEntity searchRecordEntity1 = SearchRecordEntity.builder()
                .photoShortId(1L)
                .advertisementHashKey("mcdonalds")
                .advertisementName("McDonald's")
                .categoryHashKey("restaurant")
                .categoryName("Restaurant")
                .busCompany("kmb")
                .busModelHashKey("volvo-olympian-12m")
                .busModelName("Volvo Olympian 12M")
                .routeNumber("215X")
                .licensePlateNumber("GX4965")
                .fleetPrefix("3AV")
                .fleetNumber("55")
                .thumbnail("http://thumbnail.jpg")
                .username("admin")
                .uploadedDate(12345L)
                .tags(Lists.newArrayList("mcdonalds", "3av55", "gx4965"))
                .build();
        session.save(searchRecordEntity1);

        transaction.commit();
    }
}
