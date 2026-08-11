package com.company.coursemanagement.domain.exception;

public class EnrollmentNotFoundException extends BusinessException {
    public EnrollmentNotFoundException(String id) {
        super("Inscripcion no encontrada: " + id);
    }
}
