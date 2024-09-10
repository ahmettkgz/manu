package com.example.internProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class test {

	
	@RestController
	@RequestMapping("/test")
	public class TestController {
	    @GetMapping
	    public String test() {
	        return "Test endpoint is working";
	    }
	}
}
