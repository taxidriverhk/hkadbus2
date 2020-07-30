package com.taxidriverhk.hkadbus2.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpResponseMessage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
public class GetCategoriesFunctionTest extends FunctionTestBase {

    @Mock
    private ExecutionContext context;

    @InjectMocks
    private GetCategoriesFunction function;

    @Test
    public void GIVEN_noRequestBody_WHEN_getCategories_THEN_returnListOfCategories() throws Exception {
        final HttpResponseMessage responseMessage = function.run(request, context);
        assertThat(responseMessage.getBody(), notNullValue());
    }
}
