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
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

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

    @Test
    public void GIVEN_inputFile_WHEN_import_THEN_shouldInsertEntitiesIntoDatabase() throws Exception {
        final URL testFileUrl = getClass().getClassLoader().getResource("test-photo-import-file.csv");
        final List<PhotoEntity> photoEntities = photoImportUtil.execute(Paths.get(testFileUrl.toURI()).toString());

        final Optional<PhotoEntity> photoEntity = photoRepository.getPhotoByShortId(photoEntities.get(0).getShortId());
        assertThat(photoEntity.isPresent(), equalTo(true));

        final SearchRecordResult searchRecordResult = searchPhotoProvider.searchPhotos(
                Lists.newArrayList(),
                "uploadedDate",
                "asc",
                SearchPhotoFilter.builder().build(),
                null,
                10);
        assertThat(searchRecordResult.getTotal(), greaterThanOrEqualTo(1L));
    }

    @Override
    protected void setupDataForTest(Session session) {
    }
}
