package com.bff.vikas.exception;

import com.bff.vikas.feign.dto.ApiError;

import lombok.Getter;

/**
 * Class      : BffServiceException
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Apr 20, 2026
 * Version    : 1.0
 */

@SuppressWarnings("serial")
@Getter
public class BffServiceException extends RuntimeException {
    private final ApiError apiError;

    public BffServiceException(ApiError apiError) {
        super(apiError.getMessage());
        this.apiError = apiError;
    }
}