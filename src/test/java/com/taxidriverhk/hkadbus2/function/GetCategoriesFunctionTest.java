package com.taxidriverhk.hkadbus2.function;

import com.google.common.collect.Lists;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.taxidriverhk.hkadbus2.component.CoreApiComponent;
import com.taxidriverhk.hkadbus2.model.api.GetCategoriesRequest;
import com.taxidriverhk.hkadbus2.model.api.GetCategoriesResponse;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.CATEGORY;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCategoriesFunctionTest extends FunctionTestBase<GetCategoriesRequest> {

    @Mock
    private ExecutionContext context;

    @Mock
    private CoreApiComponent coreApiComponent;

    @Mock
    private CategoryService categoryService;

    @Mock
    private GetCategoriesRequest getCategoriesRequest;

    @InjectMocks
    private GetCategoriesFunction function;

    @BeforeEach
    public void setup() {
        when(coreApiComponent.categoryService()).thenReturn(categoryService);
        function.setCoreApiComponent(coreApiComponent);
    }

    @Test
    public void GIVEN_requestBody_WHEN_getCategories_THEN_returnListOfCategories() {
        when(getCategoriesRequest.getLanguage()).thenReturn(LANGUAGE_EN);
        when(request.getBody()).thenReturn(getCategoriesRequest);
        when(categoryService.getCategories(anyString())).thenReturn(Lists.newArrayList(CATEGORY));

        final HttpResponseMessage responseMessage = function.run(request, context);
        final GetCategoriesResponse response = (GetCategoriesResponse) responseMessage.getBody();

        verify(categoryService, times(1)).getCategories(LANGUAGE_EN);
        assertThat(response.getCategories(), equalTo(Lists.newArrayList(CATEGORY)));
    }
}
