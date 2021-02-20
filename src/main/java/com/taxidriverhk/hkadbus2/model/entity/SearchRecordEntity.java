package com.taxidriverhk.hkadbus2.model.entity;

import com.taxidriverhk.hkadbus2.model.entity.identifier.SearchRecordEntityId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = EntityConstants.SEARCH_RECORD_TABLE)
@IdClass(SearchRecordEntityId.class)
public class SearchRecordEntity {

    @Id
    @Column(name = "photo_short_id")
    private Long photoShortId;

    @Id
    @Column(name = "language")
    private String language;

    @Column(name = "advertisement_hash_key")
    private String advertisementHashKey;

    @Column(name = "advertisement_name")
    private String advertisementName;

    @Column(name = "category_hash_key")
    private String categoryHashKey;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "bus_company")
    private String busCompany;

    @Column(name = "bus_model_hash_key")
    private String busModelHashKey;

    @Column(name = "bus_model_name")
    private String busModelName;

    @Column(name = "route_hash_key")
    private String routeHashKey;

    @Column(name = "route_number")
    private String routeNumber;

    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    @Column(name = "fleet_prefix")
    private String fleetPrefix;

    @Column(name = "fleet_number")
    private String fleetNumber;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "username")
    private String username;

    @Column(name = "uploaded_date")
    private Long uploadedDate;

    @Column(name = "tags")
    private String tags;
}
