package com.taxidriverhk.hkadbus2.function;

import com.google.common.annotations.VisibleForTesting;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.taxidriverhk.hkadbus2.component.CoreApiComponent;
import com.taxidriverhk.hkadbus2.component.DaggerCoreApiComponent;
import com.taxidriverhk.hkadbus2.exception.BadRequestException;
import com.taxidriverhk.hkadbus2.exception.InternalErrorException;
import com.taxidriverhk.hkadbus2.exception.ItemNotFoundException;
import com.taxidriverhk.hkadbus2.exception.UnauthorizedAccessException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class CoreApiFunctionBase<T, R> {

    @Setter(value = AccessLevel.PROTECTED)
    @VisibleForTesting
    private CoreApiComponent coreApiComponent;

    protected HttpResponseMessage executeFunction(final HttpRequestMessage request, final Function<T, R> func) {
        try {
            final T requestBody = (T) request.getBody();
            final R responseBody =  func.apply(requestBody);
            return request.createResponseBuilder(HttpStatus.OK)
                    .body(responseBody)
                    .build();
        } catch (final BadRequestException ex) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage())
                    .build();
        } catch (final UnauthorizedAccessException ex) {
            return request.createResponseBuilder(HttpStatus.UNAUTHORIZED)
                    .body(ex.getMessage())
                    .build();
        } catch (final ItemNotFoundException ex) {
            return request.createResponseBuilder(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage())
                    .build();
        } catch (final InternalErrorException ex) {
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage())
                    .build();
        }
    }

    protected CoreApiComponent getCoreApiComponent() {
        if (Objects.isNull(coreApiComponent)) {
            coreApiComponent = DaggerCoreApiComponent.builder().build();
        }
        return coreApiComponent;
    }
}
