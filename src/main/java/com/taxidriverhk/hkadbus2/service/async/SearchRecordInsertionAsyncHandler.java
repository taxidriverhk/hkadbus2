package com.taxidriverhk.hkadbus2.service.async;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.api.PutPhotoRequest;
import com.taxidriverhk.hkadbus2.model.domain.SearchRecord;
import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__({ @Inject }))
public class SearchRecordInsertionAsyncHandler {

    private static final Set<String> VALID_LANGUAGES = ImmutableSet.of("en_us", "zh_hk");

    private final SearchPhotoProvider searchPhotoProvider;

    public void insertSearchRecords(
            final PutPhotoRequest request,
            final Date uploadedDate,
            final Long photoShortId,
            final int maxRetryTimes
    ) {
        final List<CompletableFuture<Void>> searchRecordInsertionFutures = VALID_LANGUAGES.stream()
                .map(language -> CompletableFuture.runAsync(() -> {
                        log.info("Inserting search record for photo with ID {} and language {}", photoShortId, language);
                        final SearchRecord searchRecord = SearchRecord.builder()
                                .categoryId(request.getCategoryId())
                                .category(request.getCategoryNames().get(language))
                                .advertisementId(request.getAdvertisementId())
                                .advertisement(request.getAdvertisementNames().get(language))
                                .busModelId(request.getBusModelId())
                                .busModel(request.getBusModelNames().get(language))
                                .busCompany(request.getBusCompany())
                                .routeId(request.getBusRouteId())
                                .routeNumber(request.getRouteNumber())
                                .fleetPrefix(request.getFleetPrefix())
                                .fleetNumber(request.getFleetNumber())
                                .licensePlateNumber(request.getLicensePlateNumber())
                                .uploadedDate(uploadedDate.getTime())
                                .username(request.getUsername())
                                .thumbnail(request.getThumbnail())
                                .tags(generateTags(request, language))
                                .photoId(photoShortId)
                                .build();

                        int attempts = 0;
                        boolean completedSuccessfully = false;
                        while (!completedSuccessfully && attempts < maxRetryTimes) {
                            try {
                                searchPhotoProvider.insertSearchRecord(searchRecord, language);
                                completedSuccessfully = true;
                            } catch (final Exception exception) {
                                log.warn("Failed to insert search record with exception {}", exception.getMessage());
                                attempts++;
                            }
                        }

                        if (attempts >= maxRetryTimes) {
                            log.warn("Failed to insert search record for language {} after {} retries, please try inserting the search records again", language, maxRetryTimes);
                        }
                }))
                .collect(Collectors.toList());
        CompletableFuture.allOf(
                searchRecordInsertionFutures.toArray(
                    new CompletableFuture[searchRecordInsertionFutures.size()]))
                .join();
    }

    private List<String> generateTags(final PutPhotoRequest request, final String language) {
        final List<String> tags = Lists.newLinkedList();
        tags.addAll(lowerCaseAndSplitBySpace(request.getAdvertisementNames().get(language)));
        tags.addAll(lowerCaseAndSplitBySpace(request.getCategoryNames().get(language)));
        tags.addAll(lowerCaseAndSplitBySpace(request.getBusBrandNames().get(language)));
        tags.addAll(lowerCaseAndSplitBySpace(request.getBusModelNames().get(language)));
        tags.add(request.getBusCompany().toString());
        tags.add(request.getRouteNumber().toLowerCase());
        tags.add(request.getFleetPrefix().toLowerCase());
        tags.add(request.getFleetPrefix().toLowerCase() + request.getFleetNumber());
        tags.add(request.getUsername().toLowerCase());
        tags.addAll(lowerCaseAndSplitBySpace(request.getAdditionalTags()));

        return tags;
    }

    private List<String> lowerCaseAndSplitBySpace(String input) {
        if (StringUtils.isBlank(input)) {
            return Arrays.asList();
        }
        return Arrays.asList(input.toLowerCase().split(" "));
    }
}
