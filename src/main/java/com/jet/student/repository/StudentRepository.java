package com.jet.student.repository;

import com.jet.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByMatricule(String matricule);
    List<Student> findByProgramId(String programId);
}
