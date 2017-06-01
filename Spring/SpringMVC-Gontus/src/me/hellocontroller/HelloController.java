package me.hellocontroller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController{
	
	@InitBinder
	public void binder(WebDataBinder binder){
		//binder.setDisallowedFields(new String[]{"studentMobile"});
	}
	@RequestMapping(value="/admision", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable Map<String,String> val){
		
		String ex="NULL_POINTER";
		
		if(ex.equalsIgnoreCase("NULL_POINTER")){
			//throw new NullPointerException("null pointer");
		}
		
		ModelAndView model=new ModelAndView("admision");
		
		return model;
	}
	
	@ModelAttribute
	public void message(Model model){
		model.addAttribute("msg","hi, How are you ");
	}
	@RequestMapping(value="/submitadmissionform",method=RequestMethod.POST)
	public ModelAndView hi(@Valid @ModelAttribute("student") Student st, BindingResult result){
		
		if(result.hasErrors()){
			ModelAndView model=new ModelAndView("admision");
			return model;
		}
		
		ModelAndView model=new ModelAndView("HelloPage");
		
		return model;
	}

	//**************************Mail sending**************************************
	@Autowired
    private JavaMailSender mailSender;
     
	@RequestMapping(value="/mail")
	public String moveMailPage(){
		return "mail";
	}
	
    @RequestMapping(value="/sendmail")
    public String doSendEmail(HttpServletRequest request) {
        // takes input from e-mail form
        String recipientAddress = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
         
        // prints debug info
        System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
         
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        
        // sends the e-mail
        mailSender.send(email);
        System.out.println("yes");
        // forwards to the view named "Result"
        return "mail";
    }
	//****************************************************************
	
	//********************Rest API***********************************//
	@ResponseBody
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
	
	@ResponseBody
	@RequestMapping(value="/students/{name}",method=RequestMethod.GET)
	public Student getStudetnNameWise(@PathVariable("name")String studentName){
		
		Student s1=new Student();
		s1.setStudentName(studentName);
		s1.setStudentHobby("Coding");
		
		
		return s1;
		
	}
}
