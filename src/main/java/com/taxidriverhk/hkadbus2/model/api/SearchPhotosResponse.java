package com.taxidriverhk.hkadbus2.model.api;

import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SearchPhotosResponse {

    private long total;
    private List<SearchPhotoResult> results;
    private int nextPage;
}
