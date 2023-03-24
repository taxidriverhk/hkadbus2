package com.taxidriverhk.hkadbus2.service;

import com.taxidriverhk.hkadbus2.model.api.PutPhotoRequest;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;

public interface PhotoService {

    Photo getPhoto(Long photoId, String language);
    Long putPhoto(PutPhotoRequest request);
    SearchPhotoResult searchPhotos(String query, String orderBy, String sort, SearchPhotoFilter filter, Integer size, String nextSortKey);
}
