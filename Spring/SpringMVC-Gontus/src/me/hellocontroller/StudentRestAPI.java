package me.hellocontroller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class StudentRestAPI {
	//********************Rest API***********************************//
		@RequestMapping(value="/students",method=RequestMethod.GET)
		public ArrayList<Student> getStudentList(){
			
			Student s1=new Student();
			s1.setStudentName("Hardik");
			
			Student s2=new Student();
			s2.setStudentName("Gopal");
			
			Student s3=new Student();
			s3.setStudentName("Danny");
			
			ArrayList<Student> list=new ArrayList<>();
			list.add(s1);
			list.add(s2);
			list.add(s3);
			
			return list;
			
		}
		
		@RequestMapping(value="/students/{name}",method=RequestMethod.GET)
		public Student getStudetnNameWise(@PathVariable("name")String studentName){
			
			Student s1=new Student();
			s1.setStudentName(studentName);
			s1.setStudentHobby("Coding");
			
			
			return s1;
			
		}
		
		@RequestMapping(value="/student/{name}",method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Boolean> updateStudent(@PathVariable("name")String studentName,@RequestBody Student student){
			System.out.println("Student name"+studentName);
			System.out.println("studname "+student.getStudentName()+" hobby"+student.getStudentHobby());
			
			HttpHeaders h=new HttpHeaders();
			h.add("key1", "value");
			h.add("key2","value2");
			return new ResponseEntity<Boolean>(true,h,HttpStatus.OK);
		}
		
		@RequestMapping(value="/student", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Boolean> insert(@RequestBody Student student){
			System.out.println("Student name "+student.getStudentName()+" Student hobby "+student.getStudentHobby());
			
			HttpHeaders h=new HttpHeaders();
			
			h.add("location",
					ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{name}")
					.buildAndExpand(student.getStudentName())
					.toUri().toString());
			return new ResponseEntity<Boolean>(true,h,HttpStatus.OK);
		}
		
		@RequestMapping(value="/student/{name}",method=RequestMethod.DELETE)
		public ResponseEntity<Boolean> deleteRecord(@PathVariable("name") String studentName)
		{
			System.out.println("Student name "+studentName);
			
			//delete record from the DB
			
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
} 