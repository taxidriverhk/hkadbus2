package com.taxidriverhk.hkadbus2.activity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxidriverhk.hkadbus2.model.api.GetEntityOptionsResponse;
import com.taxidriverhk.hkadbus2.provider.EntityOptionsProvider;

@ExtendWith(MockitoExtension.class)
public class GetEntityOptionsActivityTest {

    @Mock
    private Map<String, String> entityOptions;

    @Mock
    private EntityOptionsProvider entityOptionsProvider;

    @InjectMocks
    private GetEntityOptionsActivity activity;

    @Test
    public void GIVEN_valiRequest_WHEN_getEntityOptions_THEN_shouldReturnValidResponse() {
        when(entityOptionsProvider.getEntityOptions(any(), any())).thenReturn(entityOptions);

        final String entityType = "advertisement";
        final Response response = activity.get(entityType, "en_us");
        final GetEntityOptionsResponse getEntityOptionsResponse = (GetEntityOptionsResponse) response.getEntity();

        assertThat(getEntityOptionsResponse.getEntityType(), equalTo(entityType));
        assertThat(getEntityOptionsResponse.getOptions(), equalTo(entityOptions));
    }
}
