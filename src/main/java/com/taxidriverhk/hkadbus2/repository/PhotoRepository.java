package com.taxidriverhk.hkadbus2.repository;

import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;

import java.util.Optional;

public interface PhotoRepository {

    Optional<PhotoEntity> getPhotoByShortId(Long shortId);
}
