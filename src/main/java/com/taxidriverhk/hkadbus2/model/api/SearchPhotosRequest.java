package com.taxidriverhk.hkadbus2.model.api;

import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchPhotosRequest {

    private int pageNumber;
    private SearchPhotoFilter filter;
}
