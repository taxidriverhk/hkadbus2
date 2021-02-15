package com.taxidriverhk.hkadbus2.model.entity.result;

import com.taxidriverhk.hkadbus2.model.entity.SearchRecordEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SearchRecordResult {

    private Long total;
    private List<SearchRecordEntity> searchRecordEntities;
}
