package me.hellocontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class HelloController extends AbstractController{
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req,HttpServletResponse res)throws Exception{
		ModelAndView model=new ModelAndView("HelloPage");
		model.addObject("welcomeMessage","Wellcome to spring ");
		return model;
	}
}
