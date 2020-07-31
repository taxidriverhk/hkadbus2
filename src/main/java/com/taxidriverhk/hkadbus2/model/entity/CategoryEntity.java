package com.taxidriverhk.hkadbus2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {

    public static final String ID = "id";
    public static final String NAME_TRANSLATION_ID = "name_translation_id";
    public static final String SKU = "sku";
    public static final String THUMBNAIL = "thumbnail";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private int id;

    @Column(name = NAME_TRANSLATION_ID)
    private int nameTranslationId;

    @Column(name = SKU)
    private String sku;

    @Column(name = THUMBNAIL)
    private String thumbnail;
}

