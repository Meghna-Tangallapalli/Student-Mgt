package com.studentpro.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.studentpro.vo.AcademicDetailsVo;
import com.studentpro.vo.StudentVo;

@Component
public interface StudentService  {

	//addAndUpdate
	String addAndUpdateRecord(StudentVo studentVo, Long id);

	StudentVo getStudent(Long id);

	String deleteDetails(Long id);

	String deleteStudent(Long id);

	String updateStudent(StudentVo studentVo, Long id);
	
	//save
	String saveStudent(StudentVo studentVo);
	//update
	String addAcademicDetails(Long id, AcademicDetailsVo academicDetailsVo);




}
