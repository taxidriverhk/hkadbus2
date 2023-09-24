package com.taxidriverhk.hkadbus2.model.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private String username;
    private String thumbnail;
    private Long registrationDate;
}
