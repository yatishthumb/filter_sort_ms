package com.hackerrank.sample.advice;

import com.hackerrank.sample.dto.StringResponse;
import com.hackerrank.sample.exception.ErrorCode;
import com.hackerrank.sample.exception.NoDataFound;
import com.hackerrank.sample.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StringResponse> handlePricingRangeValidation(ValidationException ex) {
        ErrorCode errorCode = ((ValidationException) ex).getErrorCode();
        log.error("Exception Code: {}, Description: {}",errorCode.getCode(), errorCode.getDescription());

        logExceptionStackTrace(ex);
        return new ResponseEntity(new StringResponse(ex.getErrorCode().getDescription()), BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFound.class)
    public ResponseEntity handleNoDataFoundException(HttpServletRequest request, Exception ex) {
        ErrorCode errorCode = ((NoDataFound) ex).getErrorCode();
        log.error("Exception Code: {}, Description: {}",errorCode.getCode(), errorCode.getDescription());

        logExceptionStackTrace(ex);
        return new ResponseEntity(new StringResponse(errorCode.getDescription()), NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(HttpServletRequest request, Exception ex) {
        log.error("Exception description: {}", ex.getMessage());
        logExceptionStackTrace(ex);
        return new ResponseEntity(INTERNAL_SERVER_ERROR);
    }

    private void logExceptionStackTrace(Exception ex) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("StackTrace are: ");
        for(StackTraceElement traceElement : ex.getStackTrace()) {
            stringBuilder.append(traceElement.toString()).append("\n");
        }
        log.error("StackTrace are: {}", stringBuilder);
    }
}
