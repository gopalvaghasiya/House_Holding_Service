package me.hellocontroller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value=NullPointerException.class)
	public String handleException(Exception e){
		System.out.println("Exception handle "+e);
		
		return "NullPointerException";
	}
}
