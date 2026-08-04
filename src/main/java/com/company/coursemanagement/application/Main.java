package com.company.coursemanagement.application;

import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.repository.InMemoryCourseRepository;
import com.company.coursemanagement.domain.repository.InMemoryEnrollmentRepository;
import com.company.coursemanagement.domain.repository.InMemoryStudentRepository;
import com.company.coursemanagement.presentation.CourseMenu;
import com.company.coursemanagement.presentation.EnrollmentMenu;
import com.company.coursemanagement.presentation.Menu;
import com.company.coursemanagement.presentation.StudentMenu;

public class Main {
    public static void main(String[] args) {
        InMemoryStudentRepository studentRepository = new InMemoryStudentRepository();
        InMemoryCourseRepository courseRepository = new InMemoryCourseRepository();
        InMemoryEnrollmentRepository enrollmentRepository = new InMemoryEnrollmentRepository();

        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepository,
                studentRepository, courseRepository);
}
