package ru.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.dto.CourseDto;
import ru.dto.StudentDto;
import ru.models.Course;
import ru.models.Student;
import ru.repositories.CoursesRepository;
import ru.repositories.StudentsRepository;

import java.util.List;

import static ru.dto.StudentDto.from;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;
    private final CoursesRepository coursesRepository;

    @Override
    public List<StudentDto> getStudents(int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by("id"));
        Page<Student> result = studentsRepository.findAllByIsDeletedIsNull(request);
        return from(result.getContent());
    }

    @Override
    public StudentDto addStudent(StudentDto student) {
        Student newStudent = Student.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();

        studentsRepository.save(newStudent);

        return from(newStudent);
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto student) {
        Student existedStudent = studentsRepository.getById(studentId);
        existedStudent.setFirstName(student.getFirstName());
        existedStudent.setLastName(student.getLastName());
        studentsRepository.save(existedStudent);
        return from(existedStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentsRepository.getById(studentId);
        student.setIsDeleted(true);
        studentsRepository.save(student);
    }

    @Override
    public List<CourseDto> addCourseToStudent(Long studentId, CourseDto course) {
        Student student = studentsRepository.getById(studentId);
        Course existedCourse = coursesRepository.getById(course.getId());
        student.getCourses().add(existedCourse);
        studentsRepository.save(student);

        return CourseDto.from(student.getCourses());
    }


}
