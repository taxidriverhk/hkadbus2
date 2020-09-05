package com.taxidriverhk.hkadbus2.service.impl;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Override
    public Photo getPhoto(String photoId) {
        final Optional<PhotoEntity> photoEntity = photoRepository.getPhoto(photoId);
        if (!photoEntity.isPresent()) {
            throw new ItemNotFoundException(photoId);
        }

        return Photo.builder()
                .build();
    }

    @Override
    public SearchPhotoResult searchPhotos(final SearchPhotoFilter filter, final String nextPageCursor) {
        return SearchPhotoResult.builder()
                .total(0L)
                .results(Lists.newArrayList())
                .nextPageCursor(null)
                .build();
    }
}
