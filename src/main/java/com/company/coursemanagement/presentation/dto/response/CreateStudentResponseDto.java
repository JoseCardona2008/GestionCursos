package com.company.coursemanagement.presentation.dto.response;

import java.time.LocalDate;

public record CreateStudentResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate,
        String fullName
) {
    public static CreateStudentResponseDto from(com.company.coursemanagement.domain.model.Student student) {
        return new CreateStudentResponseDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate(),
                student.getFullName()
        );
    }
}
