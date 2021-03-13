package com.taxidriverhk.hkadbus2.service.impl;

import com.google.common.base.Strings;
import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class PhotoServiceImpl implements PhotoService {

    private static final int PAGE_LIMIT = 100;
    private static final String QUERY_TEXT_SPLITTER_REGEX = "[\\s,]+";

    private final PhotoRepository photoRepository;
    private final SearchPhotoProvider searchPhotoProvider;

    @Override
    public Photo getPhoto(final Long photoId, final String language) {
        log.info("Getting photo by ID {} and language {}", photoId, language);
        final PhotoEntity photoEntity = photoRepository.getPhotoByShortId(photoId)
                .orElseThrow(() -> new ItemNotFoundException(photoId.toString()));
        return EntityMapper.INSTANCE.photoEntityToPhoto(photoEntity, language);
    }

    @Override
    public SearchPhotoResult searchPhotos(
            final String query,
            final String orderBy,
            final String sort,
            final SearchPhotoFilter filter,
            final String nextSortKey
    ) {
        final List<String> queryTexts = Strings.isNullOrEmpty(query)
                ? Collections.EMPTY_LIST
                : Arrays.asList(query.split(QUERY_TEXT_SPLITTER_REGEX));
        final SearchRecordResult searchRecordResult = searchPhotoProvider.searchPhotos(
                queryTexts,
                orderBy,
                sort,
                filter,
                nextSortKey,
                PAGE_LIMIT);

        final List<SearchRecord> searchRecords = EntityMapper.INSTANCE
                .searchRecordEntitiesToSearchRecords(searchRecordResult.getSearchRecordEntities());
        return SearchPhotoResult.builder()
                .total(searchRecordResult.getTotal())
                .results(searchRecords)
                .nextPageCursor(searchRecordResult.getNextPageCursor())
                .build();
    }
}
