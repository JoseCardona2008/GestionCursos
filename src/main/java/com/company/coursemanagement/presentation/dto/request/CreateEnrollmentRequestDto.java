package com.company.coursemanagement.presentation.dto.request;

import java.time.LocalDate;

public record CreateEnrollmentRequestDto(
        Long studentId,
        Long courseId,
        LocalDate enrollmentDate
) {}