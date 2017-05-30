package me.hellocontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController{
	
	@RequestMapping("/welcome")
	public ModelAndView hello(){
		ModelAndView model=new ModelAndView("HelloPage");
		model.addObject("msg","Helllo How are you");
		
		return model;
	}
}
