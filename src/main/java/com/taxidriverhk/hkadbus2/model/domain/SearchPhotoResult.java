package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SearchPhotoResult {

    private long total;
    private List<Photo> results;
    private String nextPageCursor;
}
