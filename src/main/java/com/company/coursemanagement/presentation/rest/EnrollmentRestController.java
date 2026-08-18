package com.company.coursemanagement.presentation.rest;

import com.company.coursemanagement.domain.model.EnrollmentStatus;
import com.company.coursemanagement.infrastructure.entity.EnrollmentEntity;
import com.company.coursemanagement.infrastructure.repository.EnrollmentJpaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripciones")
public class EnrollmentRestController {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public EnrollmentRestController(EnrollmentJpaRepository enrollmentJpaRepository) {
        this.enrollmentJpaRepository = enrollmentJpaRepository;
    }

    @GetMapping
    public List<EnrollmentEntity> listar() {
        return enrollmentJpaRepository.findAll();
    }

    @GetMapping("/estudiante/{studentId}")
    public List<EnrollmentEntity> listarPorEstudiante(@PathVariable Long studentId) {
        return enrollmentJpaRepository.findByStudentId(studentId);
    }

    @GetMapping("/curso/{courseId}")
    public List<EnrollmentEntity> listarPorCurso(@PathVariable Long courseId) {
        return enrollmentJpaRepository.findByCourseId(courseId);
    }

    @PostMapping
    public ResponseEntity<EnrollmentEntity> inscribir(@RequestParam Long studentId, @RequestParam Long courseId) {
        EnrollmentEntity enrollment = new EnrollmentEntity(null, studentId, courseId, LocalDate.now(), EnrollmentStatus.ACTIVO);
        EnrollmentEntity guardado = enrollmentJpaRepository.save(enrollment);
        return ResponseEntity.ok(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        if (enrollmentJpaRepository.existsById(id)) {
            enrollmentJpaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
