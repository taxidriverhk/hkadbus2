package com.taxidriverhk.hkadbus2.util;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxidriverhk.hkadbus2.util.PhotoImportUtil.PhotoImportFileType;

@ExtendWith(MockitoExtension.class)
public class PhotoImportUtilTest {

    @Mock
    private CloseableHttpResponse response;

    @Mock
    private CloseableHttpClient httpClient;

    @InjectMocks
    private PhotoImportUtil photoImportUtil;

    @ParameterizedTest
    @MethodSource("photoImportTestCases")
    public void GIVEN_inputFileInCsvFormat_WHEN_import_THEN_shouldInsertEntitiesIntoDatabase(
            final String inputFilePath,
            final PhotoImportFileType importFileType
    ) throws Exception {
        when(response.getEntity()).thenReturn(new StringEntity("test response string"));
        when(httpClient.execute(any(HttpPost.class))).thenReturn(response);

        // Import the CSV file
        final URL testFileUrl = getClass().getClassLoader().getResource(inputFilePath);
        photoImportUtil.execute(Paths.get(testFileUrl.toURI()).toString(), importFileType);

        // Verify that the corresponding putPhoto API calls were made
        verify(httpClient, atLeastOnce()).execute(any(HttpPost.class));
    }

    public static Stream<Arguments> photoImportTestCases() {
        return Stream.of(
                arguments(
                    "test-photo-import-file.csv",
                    PhotoImportFileType.CSV
                ),
                arguments(
                    "test-photo-import-file.xlsx",
                    PhotoImportFileType.EXCEL
                ));
    }
}
