package ru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.models.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {
}
