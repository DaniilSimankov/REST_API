package ru.services;

import ru.dto.CourseDto;
import ru.dto.StudentDto;

import java.util.List;

public interface StudentsService {
    List<StudentDto> getStudents(int page, int size);

    StudentDto addStudent(StudentDto student);

    StudentDto updateStudent(Long studentId, StudentDto student);

    void deleteStudent(Long studentId);

    List<CourseDto> addCourseToStudent(Long studentId, CourseDto course);
}
