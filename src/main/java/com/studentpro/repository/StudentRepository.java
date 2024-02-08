package com.studentpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentpro.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
