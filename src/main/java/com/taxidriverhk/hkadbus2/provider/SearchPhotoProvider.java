package com.taxidriverhk.hkadbus2.provider;

import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;

import java.util.List;

public interface SearchPhotoProvider {

    SearchRecordResult searchPhotos(
            List<String> queryTexts,
            String orderBy,
            String sort,
            SearchPhotoFilter filter,
            String nextSortKey,
            int limit
    );
}
