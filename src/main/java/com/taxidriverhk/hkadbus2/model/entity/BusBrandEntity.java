package com.taxidriverhk.hkadbus2.model.entity;

import com.taxidriverhk.hkadbus2.model.entity.converter.LocalizedStringConverter;
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
import java.util.Map;
import java.util.UUID;

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
