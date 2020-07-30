package com.taxidriverhk.hkadbus2.model.api;

import com.taxidriverhk.hkadbus2.model.domain.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetCategoriesResponse {

    private List<Category> categories;
}
