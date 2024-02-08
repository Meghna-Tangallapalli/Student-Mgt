package com.studentpro.vo;

import java.util.List;

import com.studentpro.entity.AcademicDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVo {

	private long id;

	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private List<AcademicDetailsVo > academicDetailsVo;
}
