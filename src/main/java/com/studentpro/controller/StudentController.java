package com.studentpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;import com.studentpro.entity.Student;
import com.studentpro.service.StudentService;
import com.studentpro.vo.AcademicDetailsVo;
import com.studentpro.vo.StudentVo;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//add and update api
	@PostMapping("/addAndUpdate")
	public ResponseEntity<String> addAndUpdateRecord(@RequestBody StudentVo studentVo,@RequestParam(required = false) Long id){	
		String s = studentService.addAndUpdateRecord(studentVo, id);
		return new ResponseEntity<String>(s , HttpStatus.ACCEPTED);
		
	}
	
	//review records by parent id
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<StudentVo> getStudent(@PathVariable Long id){
		StudentVo s = studentService.getStudent(id);
		return new ResponseEntity<StudentVo>(s, HttpStatus.FOUND);
		
	}
	
	//delete child record by child id
	@DeleteMapping("/deleteDetails/{id}")
	public ResponseEntity<String> deleteDetails(@PathVariable Long id){
		String s = studentService.deleteDetails(id);
		return new ResponseEntity<String>("Successfully deleted", HttpStatus.ACCEPTED);
		
	}
	//delete parent record by deleting each child record
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		String s = studentService.deleteStudent(id);
		return new ResponseEntity<String>(s , HttpStatus.ACCEPTED);
	}
	
	//edit details of parent and child
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<String> updateStudent(@RequestBody StudentVo studentVo,@PathVariable Long id){
		String student = studentService.updateStudent(studentVo, id);
		return new ResponseEntity<String>(student, HttpStatus.OK);
		
	}
	
	
	
	
	// save new records parent and list of child
	@PostMapping("/saveStudent")
	public ResponseEntity<String> saveStudent(@RequestBody StudentVo studentVo) {
		String s = studentService.saveStudent(studentVo);
		return new ResponseEntity<String>("Created successfully", HttpStatus.CREATED);

	}

	// add one more child record to existing parent record
	@PutMapping("/addAcademicDetails/{id}")
	public ResponseEntity<String> addAcademicDetails(@PathVariable Long id,
			@RequestBody AcademicDetailsVo academicDetailsVo) {
		String academics = studentService.addAcademicDetails(id, academicDetailsVo);
		return new ResponseEntity<String>(academics, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
}
