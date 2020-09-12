package com.taxidriverhk.hkadbus2.model.api;

import com.taxidriverhk.hkadbus2.model.domain.Photo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetPhotoResponse {

    private Photo photo;
}
