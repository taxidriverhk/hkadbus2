package com.taxidriverhk.hkadbus2.model.entity;

import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.taxidriverhk.hkadbus2.model.entity.converter.LocalizedStringConverter;

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
@Table(name = EntityConstants.BUS_BRAND_TABLE)
public class BusBrandEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    @Convert(converter = LocalizedStringConverter.class)
    private Map<String, String> name;

    @Column(name = "hash_key")
    private String hashKey;
}
