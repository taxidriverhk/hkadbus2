package com.taxidriverhk.hkadbus2.provider;

import java.util.List;

import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;

public interface SearchPhotoProvider {

    SearchRecordResult searchPhotos(
            List<String> queryTexts,
            String orderBy,
            String sort,
            SearchPhotoFilter filter,
            String nextSortKey,
            int limit
    );
    boolean insertSearchRecord(SearchRecord searchRecord);
}
