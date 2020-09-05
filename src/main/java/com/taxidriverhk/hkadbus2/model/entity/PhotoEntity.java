package com.taxidriverhk.hkadbus2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoEntity {

    public static final String ID = "photoId";
    public static final String THUMBNAIL = "thumbnail";
    public static final String BUS_ID = "busId";
    public static final String BUS_ROUTE_ID = "busRouteId";
    public static final String USER_ID = "userId";
    public static final String UPLOADED_DATE = "uploadedDate";

    private String photoId;
    private String thumbnail;
    private String image;
    private String busId;
    private String busRouteId;
    private String userId;
    private Long uploadedDate;
}
