package com.taxidriverhk.hkadbus2.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.exception.InternalErrorException;
import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.mapper.EntityMapper;
import com.taxidriverhk.hkadbus2.model.api.PutPhotoRequest;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoFilter;
import com.taxidriverhk.hkadbus2.model.domain.SearchPhotoResult;
import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import com.taxidriverhk.hkadbus2.model.entity.AdvertisementEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusBrandEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusModelEntity;
import com.taxidriverhk.hkadbus2.model.entity.BusRouteEntity;
import com.taxidriverhk.hkadbus2.model.entity.CategoryEntity;
import com.taxidriverhk.hkadbus2.model.entity.PhotoEntity;
import com.taxidriverhk.hkadbus2.model.entity.result.SearchRecordResult;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;
import com.taxidriverhk.hkadbus2.repository.AdvertisementRepository;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import com.taxidriverhk.hkadbus2.repository.BusRepository;
import com.taxidriverhk.hkadbus2.repository.BusRouteRepository;
import com.taxidriverhk.hkadbus2.repository.CategoryRepository;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import com.taxidriverhk.hkadbus2.repository.UserRepository;
import com.taxidriverhk.hkadbus2.service.PhotoService;
import com.taxidriverhk.hkadbus2.util.IdentifierGenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({ @Inject }))
public class PhotoServiceImpl implements PhotoService {

    private static final int PAGE_LIMIT = 100;
    private static final String QUERY_TEXT_SPLITTER_REGEX = "[\\s,]+";
    private static final Set<String> VALID_LANGUAGES = ImmutableSet.of("en_us", "zh_hk");

    private final AdvertisementRepository advertisementRepository;
    private final BusRepository busRepository;
    private final BusBrandRepository busBrandRepository;
    private final BusModelRepository busModelRepository;
    private final BusRouteRepository busRouteRepository;
    private final CategoryRepository categoryRepository;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final SearchPhotoProvider searchPhotoProvider;

    @Override
    public Photo getPhoto(final Long photoId, final String language) {
        log.info("Getting photo by ID {} and language {}", photoId, language);
        final PhotoEntity photoEntity = photoRepository.getPhotoByShortId(photoId)
                .orElseThrow(() -> new ItemNotFoundException(photoId.toString()));
        return EntityMapper.INSTANCE.photoEntityToPhoto(photoEntity, language);
    }

    @Override
    public Long putPhoto(final PutPhotoRequest request) {
        log.info("Putting photo with request {}", request);

        // Get username or throw
        final String username = request.getUsername();
        if (!userRepository.getUserByUsername(username).isPresent()) {
            throw new BadRequestException(String.format("Username not found %s", username));
        }

        final String categoryHashKey = request.getCategoryId();
        final String advertisementHashKey = request.getAdvertisementId();
        final String busBrandHashKey = request.getBusBrandId();
        final String busModelHashKey = request.getBusModelId();
        final String busRouteHashKey = request.getBusRouteId();
        final String licensePlateNumber = request.getLicensePlateNumber();

        AdvertisementEntity advertisementEntity = null;
        BusEntity busEntity = null;
        BusRouteEntity busRouteEntity = null;
        try {
            // Get or create category
            final CategoryEntity categoryEntity = getOrCreateEntity(
                    () -> categoryRepository.getCategoryByHashKey(categoryHashKey),
                    entity -> categoryRepository.putCategory(entity),
                    CategoryEntity.builder()
                            .build(),
                    CategoryEntity.class);
            // Get or create advertisement
            advertisementEntity = getOrCreateEntity(
                    () -> advertisementRepository.getAdvertisementByHashKey(advertisementHashKey),
                    entity -> advertisementRepository.putAdvertisement(entity),
                    AdvertisementEntity.builder()
                            .category(categoryEntity)
                            .hashKey(advertisementHashKey)
                            .build(),
                    AdvertisementEntity.class);
            // Get or create bus brand
            final BusBrandEntity busBrandEntity = getOrCreateEntity(
                    () -> busBrandRepository.getBusBrandByHashKey(busBrandHashKey),
                    entity -> busBrandRepository.putBusBrand(entity),
                    BusBrandEntity.builder()
                            .build(),
                    BusBrandEntity.class);
            // Get or create bus model
            final BusModelEntity busModelEntity = getOrCreateEntity(
                    () -> busModelRepository.getBusModelByHashKey(busModelHashKey),
                    entity -> busModelRepository.putBusModel(entity),
                    BusModelEntity.builder()
                            .busBrand(busBrandEntity)
                            .build(),
                    BusModelEntity.class);
            // Get or create bus by license plate number
            busEntity = getOrCreateEntity(
                    () -> busRepository.getBuses(busModelEntity.getId().toString())
                            .stream()
                            .filter(entity -> entity.getLicensePlateNumber().equals(licensePlateNumber))
                            .findFirst(),
                    entity -> busRepository.putBus(entity),
                    BusEntity.builder()
                            .busModel(busModelEntity)
                            .licensePlateNumber(licensePlateNumber)
                            .build(),
                    BusEntity.class);
            // Get or create bus route
            busRouteEntity = getOrCreateEntity(
                    () -> busRouteRepository.getBusRouteByHashKey(busRouteHashKey),
                    entity -> busRouteRepository.putBusRoute(entity),
                    BusRouteEntity.builder()
                            .hashKey(busRouteHashKey)
                            .build(),
                    BusRouteEntity.class);
        } catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new InternalErrorException(String.format("Failed to get or create related entities: %s", ex.getMessage()));
        }

        // Generate photo short ID
        // Check if photo short ID is already used
        boolean photoShortIdAvailable = true;
        Long photoShortId = null;
        do {
            photoShortId = IdentifierGenerator.generateNumericIdentifier(UUID.randomUUID().toString());
            photoShortIdAvailable = !photoRepository.getPhotoByShortId(photoShortId).isPresent();
        } while (!photoShortIdAvailable);

        // Create photo
        try {
            final PhotoEntity photoEntity = PhotoEntity.builder()
                    .advertisement(advertisementEntity)
                    .shortId(photoShortId)
                    .build();
            photoShortId = photoRepository.putPhoto(photoEntity);

            // Create search record
            final List<SearchRecord> searchRecords = VALID_LANGUAGES.stream()
                    .map(language -> SearchRecord.builder()
                            .build())
                    .collect(Collectors.toList());
            searchRecords.stream()
                    .forEach(searchRecord -> searchPhotoProvider.insertSearchRecord(searchRecord));

            return photoShortId;
        } catch (final Exception ex) {
            // TODO: rollback if necessary to avoid dirty data
            throw new InternalErrorException(String.format("Failed to insert photo and corresponding search records: %s", ex.getMessage()));
        }
    }

    @Override
    public SearchPhotoResult searchPhotos(
            final String query,
            final String orderBy,
            final String sort,
            final SearchPhotoFilter filter,
            final Integer size,
            final String nextSortKey
    ) {
        final List<String> queryTexts = Strings.isNullOrEmpty(query)
                ? Collections.emptyList()
                : Arrays.asList(query.split(QUERY_TEXT_SPLITTER_REGEX));
        final Integer sizeToUse = ObjectUtils.defaultIfNull(size, PAGE_LIMIT);
        final SearchRecordResult searchRecordResult = searchPhotoProvider.searchPhotos(
                queryTexts,
                orderBy,
                sort,
                filter,
                nextSortKey,
                sizeToUse.intValue());

        final List<SearchRecord> searchRecords = EntityMapper.INSTANCE
                .searchRecordEntitiesToSearchRecords(searchRecordResult.getSearchRecordEntities());
        return SearchPhotoResult.builder()
                .total(searchRecordResult.getTotal())
                .results(searchRecords)
                .nextPageCursor(searchRecordResult.getNextPageCursor())
                .build();
    }

    private <T> T getOrCreateEntity(
            final Supplier<Optional<T>> entityGetter,
            final Function<T, String> entityInsertFunc,
            final T entityToInsert,
            final Class<T> entityClass
    ) throws IllegalAccessException,
             InvocationTargetException,
             NoSuchMethodException
    {
        final Optional<T> entityOptional = entityGetter.get();
        if (entityOptional.isPresent()) {
            return entityOptional.get();
        } else {
            final UUID insertEntityKey = UUID.fromString(entityInsertFunc.apply(entityToInsert));
            final Method setIdMethod = entityClass.getMethod("setId", UUID.class);
            setIdMethod.invoke(entityToInsert, insertEntityKey);
            return entityToInsert;
        }
    }
}
