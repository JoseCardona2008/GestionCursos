package com.company.coursemanagement.domain.exception;

public class DuplicateCodeException extends BusinessException {
    public DuplicateCodeException(String message) {
        super(message);
    }
}