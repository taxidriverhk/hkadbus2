package com.taxidriverhk.hkadbus2.model.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = EntityConstants.BUS_TABLE)
public class BusEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "short_id")
    private Long shortId;

    @Column(name = "bus_company")
    private String busCompany;

    @Column(name = "fleet_prefix")
    private String fleetPrefix;

    @Column(name = "fleet_number")
    private String fleetNumber;

    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    @ManyToOne
    @JoinColumn(name = "bus_model_id", nullable = false)
    private BusModelEntity busModel;
}
