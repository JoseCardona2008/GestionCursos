package com.company.coursemanagement.application.service;

import com.company.coursemanagement.domain.exception.*;
import com.company.coursemanagement.domain.model.*;
import com.company.coursemanagement.domain.repository.CourseRepository;
import com.company.coursemanagement.domain.repository.EnrollmentRepository;
import com.company.coursemanagement.domain.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment inscribir(Long studentId, Long courseId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException("Estudiante no encontrado con id: " + studentId);
        }
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException("Curso no encontrado con id: " + courseId);
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Curso no encontrado con id: " + courseId));

        long inscritos = enrollmentRepository.findByCourseId(courseId).stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.ACTIVE)
                .count();

        if (inscritos >= course.getMaxCapacity()) {
            throw new BusinessException("El curso ha alcanzado su capacidad maxima");
        }

        Enrollment enrollment = new Enrollment(studentId, courseId, LocalDate.now(), EnrollmentStatus.ACTIVE);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> listarPorEstudiante(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> listarPorCurso(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public List<Enrollment> listarTodos() {
        return enrollmentRepository.findAll();
    }

    public void cancelarInscripcion(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException("Inscripcion no encontrada con id: " + id));
        enrollmentRepository.deleteById(id);
    }
}