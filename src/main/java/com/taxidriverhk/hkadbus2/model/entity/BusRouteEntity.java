package com.taxidriverhk.hkadbus2.model.entity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.taxidriverhk.hkadbus2.model.entity.converter.LocalizedStringConverter;
import com.taxidriverhk.hkadbus2.model.entity.converter.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = EntityConstants.BUS_ROUTE_TABLE)
public class BusRouteEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "route_number")
    private String routeNumber;

    @Column(name = "bus_companies")
    @Convert(converter = StringListConverter.class)
    private List<String> busCompanies;

    @Column(name = "start_location")
    @Convert(converter = LocalizedStringConverter.class)
    private Map<String, String> startLocation;

    @Column(name = "end_location")
    @Convert(converter = LocalizedStringConverter.class)
    private Map<String, String> endLocation;

    @Column(name = "hash_key")
    private String hashKey;
}
