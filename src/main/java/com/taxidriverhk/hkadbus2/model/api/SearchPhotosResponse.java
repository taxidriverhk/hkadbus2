package com.taxidriverhk.hkadbus2.model.api;

import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SearchPhotosResponse {

    private Long total;
    private List<SearchRecord> results;
    private String nextPageCursor;
}
