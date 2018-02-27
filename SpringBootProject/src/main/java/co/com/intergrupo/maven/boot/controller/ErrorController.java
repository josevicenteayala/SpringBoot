package co.com.intergrupo.maven.boot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
	public static final String PAGINA_ERROR_500 = "error/500";

	@ExceptionHandler(Exception.class)
	public String showInternalError(Exception exception) {
		System.out.println("Ocurrio un error---> "+exception.getMessage());		
		return PAGINA_ERROR_500;
	}
}
