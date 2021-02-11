package com.taxidriverhk.hkadbus2.activity;

import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.model.api.GetCategoriesResponse;
import com.taxidriverhk.hkadbus2.model.domain.Category;
import com.taxidriverhk.hkadbus2.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCategoriesActivityTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private Category category;

    @InjectMocks
    private GetCategoriesActivity activity;

    @Test
    public void GIVEN_validLanguage_WHEN_getCategories_THEN_shouldReturnValidResponse() {
        when(categoryService.getCategories(any())).thenReturn(Collections.singletonList(category));

        final Response response = activity.list(LANGUAGE_EN);

        verify(categoryService, times(1)).getCategories(LANGUAGE_EN);
        final GetCategoriesResponse getCategoriesResponse = (GetCategoriesResponse) response.getEntity();
        assertThat(getCategoriesResponse.getCategories(), containsInAnyOrder(category));
    }

    @Test
    public void GIVEN_invalidLanguage_WHEN_getCategories_THEN_shouldThrowException() {
        assertThrows(BadRequestException.class, () -> activity.list("invalid-language"));
    }
}
