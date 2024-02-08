package com.studentpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentpro.entity.AcademicDetails;

@Repository
public interface AcademicDetailsRepository extends JpaRepository<AcademicDetails, Long> {

	List<AcademicDetails> findByStudentId(Long studentId);

}
