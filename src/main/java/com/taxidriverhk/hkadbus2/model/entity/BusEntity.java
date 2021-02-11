package com.taxidriverhk.hkadbus2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

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

    @GeneratedValue(generator = "hilo")
    @GenericGenerator(
            name = "hilo",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
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
