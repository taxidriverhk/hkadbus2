package com.taxidriverhk.hkadbus2.model.entity.identifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRecordEntityId implements Serializable {

    private Long photoShortId;
    private String language;
}
