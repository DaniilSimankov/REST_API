package ru.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.models.Student;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByIsDeletedIsNull(Pageable pageable);
}
