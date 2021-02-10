package com.taxidriverhk.hkadbus2.model.entity;

import com.taxidriverhk.hkadbus2.model.entity.converter.LocalizedStringConverter;
import com.taxidriverhk.hkadbus2.model.entity.converter.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
}
