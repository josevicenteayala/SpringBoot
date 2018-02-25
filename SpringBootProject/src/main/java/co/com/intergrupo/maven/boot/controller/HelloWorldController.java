package co.com.intergrupo.maven.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class HelloWorldController {

	@GetMapping("/helloworld")
	public String helloWorld() {
		return "helloworld";
	}
	
}
