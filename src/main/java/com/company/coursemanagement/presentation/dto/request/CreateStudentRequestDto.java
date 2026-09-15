package com.company.coursemanagement.presentation.dto.request;

import java.time.LocalDate;

public record CreateStudentRequestDto(
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate
) {}
