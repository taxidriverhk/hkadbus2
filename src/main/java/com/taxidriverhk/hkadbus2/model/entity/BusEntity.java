package com.taxidriverhk.hkadbus2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusEntity {

    public static final String BUS_ID = "busId";
    public static final String BUS_COMPANY = "busCompany";
    public static final String BUS_MODEL_ID = "busModelId";
    public static final String FLEET_PREFIX = "fleetPrefix";
    public static final String FLEET_NUMBER = "fleetNumber";
    public static final String LICENSE_PLATE_NUMBER = "licensePlateNumber";

    private String busId;
    private String busCompany;
    private String busModelId;
    private String fleetPrefix;
    private Integer fleetNumber;
    private String licensePlateNumber;
}
