package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SearchPhotoResult {

    private Long total;
    private List<SearchRecord> results;
    private String nextPageCursor;
}
