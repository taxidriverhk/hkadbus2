package com.taxidriverhk.hkadbus2.service.impl;

import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.model.domain.Photo;
import com.taxidriverhk.hkadbus2.repository.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_SHORT_ID_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceImplTest {

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoServiceImpl photoService;

    @Test
    public void WHEN_getPhotoById_THEN_shouldGetDataFromCorrespondingRepositories() {
        when(photoRepository.getPhotoByShortId(any())).thenReturn(Optional.of(PHOTO_ENTITY_1));
        final Photo photo = photoService.getPhoto(PHOTO_SHORT_ID_1, LANGUAGE_EN);
        assertThat(photo, equalTo(PHOTO_1));
    }

    @Test
    public void WHEN_getPhotoByInvalidId_THEN_shouldThrowItemNotFoundException() {
        when(photoRepository.getPhotoByShortId(any())).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> photoService.getPhoto(PHOTO_SHORT_ID_1, LANGUAGE_EN));
    }
}
