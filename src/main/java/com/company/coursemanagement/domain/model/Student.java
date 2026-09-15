package com.company.coursemanagement.domain.model;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public final class Student {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDate birthDate;

    public Student(Long id, String firstName, String lastName, String email, LocalDate birthDate) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("el nombre no puede ser nulo o vacío");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("el apellido no puede ser nulo o vacío");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("el correo no puede ser nulo o vacío");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("la fecha de nacimiento no puede ser nula");
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Student(String firstName, String lastName, String email, LocalDate birthDate) {
        this(null, firstName, lastName, email, birthDate);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Student withId(Long id) {
        return new Student(id, firstName, lastName, email, birthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(id, student.id) && Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Estudiante{id=" + id + ", nombre='" + firstName + '\'' +
                ", apellido='" + lastName + '\'' + ", correo='" + email + '\'' +
                ", fechaNacimiento=" + birthDate + '}';
    }

}
