/**
 * 
 */
package com.spring.boot.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vin00
 *
 */
@RestController(value="DockerExample")
@RequestMapping(path="/DockerExample")
public class DockerController {

	@GetMapping("/dockerMethod.do")
	public String dockerMethod() {
		return "This is a test from docker service controller";
	}
	
}
