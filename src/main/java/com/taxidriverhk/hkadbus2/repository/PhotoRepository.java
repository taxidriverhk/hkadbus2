package com.taxidriverhk.hkadbus2.repository;

import java.util.Optional;

import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;

public interface PhotoRepository {

    Optional<PhotoEntity> getPhotoByShortId(Long shortId);
    Long putPhoto(PhotoEntity photo);
}
