package com.taxidriverhk.hkadbus2.service.impl;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.model.domain.BusCompany;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.repository.BusRepository;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class PhotoServiceImpl implements PhotoService {

    private final AdvertisementRepository advertisementRepository;
    private final BusRepository busRepository;
    private final BusModelRepository busModelRepository;
    private final BusRouteRepository busRouteRepository;
    private final PhotoRepository photoRepository;

    @Override
    public Photo getPhoto(final String photoId, final String language) {
        final Optional<PhotoEntity> photoEntityOptional = photoRepository.getPhoto(photoId);
        if (!photoEntityOptional.isPresent()) {
            throw new ItemNotFoundException(photoId);
        }

        final PhotoEntity photoEntity = photoEntityOptional.get();

        final String advertisementId = photoEntity.getAdvertisementId();
        final AdvertisementEntity advertisementEntity = advertisementRepository.getAdvertisement(advertisementId).get();

        final String busId = photoEntity.getBusId();
        final BusEntity busEntity = busRepository.getBus(busId).get();

        final String busModelId = busEntity.getBusModelId();
        final BusModelEntity busModelEntity = busModelRepository.getBusModelByHashKey(busModelId).get();

        final String busRouteId = photoEntity.getBusRouteId();
        final BusRouteEntity busRouteEntity = busRouteRepository.getBusRouteByHashKey(busRouteId).get();

        return Photo.builder()
                .photoId(photoEntity.getPhotoId())
                .advertisementId(advertisementEntity.getHashKey())
                .advertisement(advertisementEntity.getName().get(language))
                .busCompany(BusCompany.fromString(busEntity.getBusCompany()))
                .busModelId(busModelEntity.getHashKey())
                .busModel(busModelEntity.getName().get(language))
                .busRouteId(busRouteEntity.getHashKey())
                .busRoute(busRouteEntity.getRouteNumber())
                .licensePlateNumber(busEntity.getLicensePlateNumber())
                .fleetPrefix(busEntity.getFleetPrefix())
                .fleetNumber(busEntity.getFleetNumber())
                .image(photoEntity.getImage())
                .thumbnail(photoEntity.getThumbnail())
                .username(photoEntity.getUserId())
                .uploadedDate(photoEntity.getUploadedDate())
                .build();
    }

    @Override
    public SearchPhotoResult searchPhotos(final SearchPhotoFilter filter, final String nextPageCursor) {
        return SearchPhotoResult.builder()
                .total(0L)
                .results(Lists.newArrayList())
                .nextPageCursor(null)
                .build();
    }
}
