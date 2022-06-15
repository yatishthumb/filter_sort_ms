package com.hackerrank.sample.validator;

import com.hackerrank.sample.exception.ErrorCode;
import com.hackerrank.sample.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validatePriceRange(int initPrice, int finalPrice) {

        if(initPrice > finalPrice) {
            throw new ValidationException(ErrorCode.PRICING_RANGE);
        }
    }
}
