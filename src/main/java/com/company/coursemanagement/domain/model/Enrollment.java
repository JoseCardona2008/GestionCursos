package com.company.coursemanagement.domain.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Enrollment {
    private final Long id;
    private final Long studentId;
    private final Long courseId;
    private final LocalDate enrollmentDate;
    private final EnrollmentStatus status;

    public Enrollment(Long id, Long studentId, Long courseId, LocalDate enrollmentDate, EnrollmentStatus status) {
        if (studentId == null) {
            throw new IllegalArgumentException("el id del estudiante no puede ser nulo");
        }
        if (courseId == null) {
            throw new IllegalArgumentException("el id del curso no puede ser nulo");
        }
        if (enrollmentDate == null) {
            throw new IllegalArgumentException("la fecha de inscripción no puede ser nula");
        }
        if (status == null) {
            throw new IllegalArgumentException("el estado no puede ser nulo");
        }
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public Enrollment(Long studentId, Long courseId, LocalDate enrollmentDate, EnrollmentStatus status) {
        this(null, studentId, courseId, enrollmentDate, status);
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public Enrollment withId(Long id) {
        return new Enrollment(id, studentId, courseId, enrollmentDate, status);
    }

    public Enrollment withStatus(EnrollmentStatus status) {
        return new Enrollment(id, studentId, courseId, enrollmentDate, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment enrollment)) return false;
        return Objects.equals(id, enrollment.id) && Objects.equals(studentId, enrollment.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId);
    }

    @Override
    public String toString() {
        return "Inscripcion{id=" + id + ", idEstudiante=" + studentId +
                ", idCurso=" + courseId + ", fechaInscripcion=" + enrollmentDate +
                ", estado=" + status + '}';
    }
}
