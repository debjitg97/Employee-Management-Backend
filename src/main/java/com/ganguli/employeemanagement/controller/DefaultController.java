package com.ganguli.employeemanagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DefaultController {
	@RequestMapping("/")
	private String getDefaultHTML() {
		return "<h1>Employee Management APIs are hosted here!</h1>";
	}
}
