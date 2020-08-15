package com.taxidriverhk.hkadbus2.model.api;

import com.taxidriverhk.hkadbus2.model.domain.Advertisement;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetAdvertisementsResponse {
    
    private List<Advertisement> advertisements;
}
