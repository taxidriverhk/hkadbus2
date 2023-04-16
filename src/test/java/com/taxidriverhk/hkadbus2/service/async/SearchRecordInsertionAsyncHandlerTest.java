package com.taxidriverhk.hkadbus2.service.async;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PHOTO_SHORT_ID_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.PUT_PHOTO_REQUEST;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxidriverhk.hkadbus2.provider.SearchPhotoProvider;

@ExtendWith(MockitoExtension.class)
public class SearchRecordInsertionAsyncHandlerTest {

    @Mock
    private SearchPhotoProvider searchPhotoProvider;

    @InjectMocks
    private SearchRecordInsertionAsyncHandler handler;
    
    @Test
    public void GIVEN_photoRequest_WHEN_insertSearchRecords_THEN_shouldAttemptToInsert() {
        final Date uploadedDate = Date.from(Instant.now());
        handler.insertSearchRecords(PUT_PHOTO_REQUEST, uploadedDate, PHOTO_SHORT_ID_1, 3);

        verify(searchPhotoProvider, times(2)).insertSearchRecord(any(), any());
    }

    @Test
    public void GIVEN_photoRequest_WHEN_insertSearchRecordFails_THEN_shouldPassGracefully() {
        doThrow(new RuntimeException()).when(searchPhotoProvider).insertSearchRecord(any(), any());
        assertDoesNotThrow(() -> handler.insertSearchRecords(PUT_PHOTO_REQUEST, new Date(12345L), PHOTO_SHORT_ID_1, 3));
    }
}
