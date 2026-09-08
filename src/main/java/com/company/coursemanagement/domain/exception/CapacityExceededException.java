package com.company.coursemanagement.domain.exception;

public class CapacityExceededException extends BusinessException {
    public CapacityExceededException(String message) {
        super(message);
    }
}