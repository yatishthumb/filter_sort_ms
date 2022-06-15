package com.hackerrank.sample.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

        PRICING_RANGE(123, "Validation error has occurred as initial price range should be equal or less then final price range."),
        PRODUCT_DATA_NOT_FOUND(234, "No Product data available."),
        JSON_EMPTY_OR_MALFORMED(345, "JSON data is empty or malformed.");

        private final int code;
        private final String description;
}
