package com.taxidriverhk.hkadbus2.util;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;
import com.taxidriverhk.hkadbus2.provider.impl.SearchPhotoProviderImpl;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.repository.impl.PhotoSqlRepository;
import com.taxidriverhk.hkadbus2.repository.impl.SqlRepositoryTestBase;
import com.taxidriverhk.hkadbus2.util.PhotoImportUtil.PhotoImportFileType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.ADVERTISEMENT_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_ROUTE_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.USER_ENTITY_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PhotoImportUtilTest extends SqlRepositoryTestBase {

    private PhotoImportUtil photoImportUtil;
    private PhotoRepository photoRepository;
    private SearchPhotoProvider searchPhotoProvider;

    @BeforeEach
    public void setup() {
        photoImportUtil = new PhotoImportUtil(sessionFactory);
        photoRepository = new PhotoSqlRepository(sessionFactory);
        searchPhotoProvider = new SearchPhotoProviderImpl(sessionFactory);
    }

    @ParameterizedTest
    @MethodSource("photoImportTestCases")
    public void GIVEN_inputFileInCsvFormat_WHEN_import_THEN_shouldInsertEntitiesIntoDatabase(
            final String inputFilePath,
            final PhotoImportFileType importFileType
    ) throws Exception {
        // Insert a single bus entity to check to ensure the duplicate check works
        final Session session = sessionFactory.openSession();
        final Transaction insertBusTransaction = session.beginTransaction();
        session.save(CATEGORY_ENTITY_1);
        session.save(ADVERTISEMENT_ENTITY_1);
        session.save(BUS_BRAND_ENTITY_1);
        session.save(BUS_MODEL_ENTITY_1);
        session.save(BUS_ENTITY_1);
        session.save(USER_ENTITY_1);
        session.save(BUS_ROUTE_ENTITY_1);

        insertBusTransaction.commit();

        // Import the CSV file
        final URL testFileUrl = getClass().getClassLoader().getResource(inputFilePath);
        final List<PhotoEntity> photoEntities = photoImportUtil.execute(Paths.get(testFileUrl.toURI()).toString(), importFileType);

        // Verify that the photo entity is found
        final Optional<PhotoEntity> photoEntity = photoRepository.getPhotoByShortId(photoEntities.get(0).getShortId());
        assertThat(photoEntity.isPresent(), equalTo(true));

        final SearchRecordResult searchRecordResult = searchPhotoProvider.searchPhotos(
                Lists.newArrayList(),
                "uploadedDate",
                "asc",
                SearchPhotoFilter.builder().build(),
                null,
                10);
        assertThat(searchRecordResult.getSearchRecordEntities().size(), equalTo(10));
        assertThat(searchRecordResult.getTotal(), greaterThanOrEqualTo(1L));
    }

    public static Stream<Arguments> photoImportTestCases() {
        return Stream.of(
                arguments(
                    "test-photo-import-file.csv",
                    PhotoImportFileType.CSV
                ),
                arguments(
                    "test-photo-import-file.xlsx",
                    PhotoImportFileType.EXCEL
                ));
    }

    @Override
    protected void setupDataForTest(Session session) {
    }
}
