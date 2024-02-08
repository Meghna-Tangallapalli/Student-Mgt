package com.studentpro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentpro.entity.AcademicDetails;
import com.studentpro.entity.Student;
import com.studentpro.repository.AcademicDetailsRepository;
import com.studentpro.repository.StudentRepository;
import com.studentpro.vo.AcademicDetailsVo;
import com.studentpro.vo.StudentVo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AcademicDetailsRepository academicDetailsRepository;

	@Override
	public String addAndUpdateRecord(StudentVo studentVo, Long id) {
		
		if (id == null) {
						
			Student student = new Student();
			student.setName(studentVo.getName());
			student.setEmail(studentVo.getEmail());
			student.setPhoneNumber(studentVo.getPhoneNumber());
			student.setAddress(studentVo.getAddress());
			Student save = studentRepository.save(student);

			studentVo.getAcademicDetailsVo().stream().forEach(academicsVo -> {

				AcademicDetails academics = new AcademicDetails();
				academics.setEducation(academicsVo.getEducation());
				academics.setPercentage(academicsVo.getPercentage());
				academics.setYear(academicsVo.getYear());
				academics.setInstitute(academicsVo.getInstitute());
				academics.setStudent(save);

				AcademicDetails academicsSave = academicDetailsRepository.save(academics);

			});
			return "Created successfully";	
			
		}else {
			if (id != null) {
				Optional<Student> stuOp = studentRepository.findById(id);
				
				if (stuOp.isPresent()) {
					Student student = stuOp.get();
					
					studentVo.getAcademicDetailsVo().stream().forEach(academicsVo -> {

					AcademicDetails newRecord = new AcademicDetails();
					newRecord.setEducation(academicsVo .getEducation());
					newRecord.setInstitute(academicsVo .getInstitute());
					newRecord.setPercentage(academicsVo .getPercentage());
					newRecord.setYear(academicsVo .getYear());
					newRecord.setStudent(student);
					
					AcademicDetails savedDetails = academicDetailsRepository.save(newRecord);
					});
				}
				
			}				
		}
		return "Successfully added new academic details record";
		
	}

	

	@Override
	public StudentVo getStudent(Long id) {

		Optional<Student> studentOpt = studentRepository.findById(id);

		StudentVo studentVo = null;
		if (studentOpt.isPresent()) {

			Student student = studentOpt.get();
			List<AcademicDetails> academics = academicDetailsRepository.findByStudentId(id);

			List<AcademicDetailsVo> academicsVoList = new ArrayList<>();

			academics.stream().forEach(a -> {

				AcademicDetailsVo academicVO = new AcademicDetailsVo();
				academicVO.setEducation(a.getEducation());
				academicVO.setInstitute(a.getInstitute());
				academicVO.setPercentage(a.getPercentage());
				academicVO.setYear(a.getYear());
				academicsVoList.add(academicVO);

			});

			studentVo = new StudentVo();
			studentVo.setName(student.getName());
			studentVo.setEmail(student.getEmail());
			studentVo.setAddress(student.getAddress());
			studentVo.setAcademicDetailsVo(academicsVoList);

		}
		return studentVo;

	}

	@Override
	public String deleteDetails(Long id) {
		
		Optional<AcademicDetails> academicOpt = academicDetailsRepository.findById(id);
		
		if (academicOpt.isPresent()) {
			
			AcademicDetails academicD = academicOpt.get();
			
			academicDetailsRepository.deleteById(id);
			
		}
		
		return "Successfully deleted" ;
	}

	@Override
	public String deleteStudent(Long id) {

		Optional<Student> studentOpt = studentRepository.findById(id);

		if (studentOpt.isPresent()) {

			Student stu = studentOpt.get();

			List<AcademicDetails> academicD = academicDetailsRepository.findByStudentId(id);

			academicD.stream().forEach(a -> {
				academicDetailsRepository.deleteById(a.getId());

			});

			studentRepository.deleteById(id);

		}
		return "Record Successfully deleted";
	}

	@Override
	public String updateStudent(StudentVo studentVo, Long id) {
		Optional<Student> stuOpt = studentRepository.findById(id);
		if (stuOpt.isPresent()) {
			Student student = stuOpt.get();
					
		
		 student.setEmail(studentVo.getEmail());
		 student.setPhoneNumber(studentVo.getPhoneNumber());
		 student.setAddress(studentVo.getAddress());
		 
		 Student save = studentRepository.save(student);

			studentVo.getAcademicDetailsVo().stream().forEach(academicsVo -> {

				Optional<AcademicDetails> academicOp = academicDetailsRepository.findById(academicsVo.getId());
				
			if (academicOp.isPresent()) {
				
				AcademicDetails academicDt = academicOp.get();
				
				academicDt.setId(academicsVo.getId());
				academicDt.setEducation(academicsVo.getEducation());
				academicDt.setPercentage(academicsVo.getPercentage());
				academicDt.setYear(academicsVo.getYear());
				academicDt.setInstitute(academicsVo.getInstitute());
				academicDt.setStudent(save);

				AcademicDetails academicsSave = academicDetailsRepository.save(academicDt);
			};
								
		});
		
		
	}
		return "Updated successfully";
	}


	
	

	@Override
	public String saveStudent(StudentVo studentVo) {
				
		Student student = new Student();
		student.setName(studentVo.getName());
		student.setEmail(studentVo.getEmail());
		student.setPhoneNumber(studentVo.getEmail());
		student.setAddress(studentVo.getAddress());
		Student save = studentRepository.save(student);

		studentVo.getAcademicDetailsVo().stream().forEach(academicsVo -> {

			AcademicDetails academics = new AcademicDetails();
			academics.setEducation(academicsVo.getEducation());
			academics.setPercentage(academicsVo.getPercentage());
			academics.setYear(academicsVo.getYear());
			academics.setInstitute(academicsVo.getInstitute());
			academics.setStudent(save);

			AcademicDetails academicsSave = academicDetailsRepository.save(academics);

		});

		return "Created successfully";

	}

	@Override
	public String addAcademicDetails(Long id, AcademicDetailsVo academicDetailsVo) {
		Optional<Student> studentOp = studentRepository.findById(id);
		
		if (studentOp.isPresent()) {
			Student student = studentOp.get();
			
			AcademicDetails newRecord = new AcademicDetails();
			newRecord.setEducation(academicDetailsVo.getEducation());
			newRecord.setInstitute(academicDetailsVo.getInstitute());
			newRecord.setPercentage(academicDetailsVo.getPercentage());
			newRecord.setYear(academicDetailsVo.getYear());
			newRecord.setStudent(student);
			
			AcademicDetails savedDetails = academicDetailsRepository.save(newRecord);
			
		}
		return "Successfully added new academic details record";
	}
	
}
