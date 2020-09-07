package com.taxidriverhk.hkadbus2.service.impl;

import com.google.common.collect.Lists;
import com.taxidriverhk.hkadbus2.model.domain.BusModel;
import com.taxidriverhk.hkadbus2.repository.BusBrandRepository;
import com.taxidriverhk.hkadbus2.repository.BusModelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_BRAND_HASH_KEY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.BUS_MODEL_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BusServiceImplTest {

    @Mock
    private BusBrandRepository busBrandRepository;

    @Mock
    private BusModelRepository busModelRepository;

    @InjectMocks
    private BusServiceImpl busService;

    @Test
    public void WHEN_getAllBusModels_THEN_shouldGetEntitiesFromRepository() {
        when(busBrandRepository.getBusBrands()).thenReturn(Lists.newArrayList(BUS_BRAND_ENTITY_1, BUS_BRAND_ENTITY_2));
        when(busModelRepository.getBusModelsByBrandId(anyString())).thenAnswer(invocationOnMock -> {
            final String busBrandHashKey = invocationOnMock.getArgument(0);
            if (busBrandHashKey.equals(BUS_BRAND_HASH_KEY_1)) {
                return Lists.newArrayList(BUS_MODEL_ENTITY_1);
            }
            return Lists.newArrayList(BUS_MODEL_ENTITY_2);
        });

        final List<BusModel> busModels = busService.getBusModels(LANGUAGE_EN);
        assertThat(busModels, containsInAnyOrder(BUS_MODEL_1, BUS_MODEL_2));
    }
}
