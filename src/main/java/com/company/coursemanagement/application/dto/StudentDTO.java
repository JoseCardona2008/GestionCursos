package com.company.coursemanagement.application.dto;

import java.time.LocalDate;

public record StudentDTO(String firstName, String lastName, String email, LocalDate birthDate) {
}
