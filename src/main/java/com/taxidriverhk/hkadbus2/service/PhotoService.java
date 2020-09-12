package com.taxidriverhk.hkadbus2.service;

import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;

public interface PhotoService {

    Photo getPhoto(String photoId, String language);
    SearchPhotoResult searchPhotos(SearchPhotoFilter filter, String nextPageCursor);
}
