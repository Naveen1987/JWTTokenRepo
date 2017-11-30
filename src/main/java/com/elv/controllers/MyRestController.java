package com.elv.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elv.config.TokenAuthenticationService;
import com.elv.models.Student;
import com.elv.services.StudentService;

@RestController
public class MyRestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	StudentService std;
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;
		@GetMapping("/getall")
	public List<Student>getAll(){
		return std.getStudents();
	}
		@GetMapping("/getId")
		public Student getId(@RequestParam long id){
			return std.getStudent(id);
		}
		
		@GetMapping("/signout")
		public String getId(HttpServletRequest req){
			tokenAuthenticationService.revokeToken(req);
			return "Successfully logout";
		}
	
}
