package me.hellocontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import me.dao.StudentDAO;

@Controller
public class HelloController{
	
	
	@Autowired
	StudentDAO dao;
	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String adminPanle(){
		//dao.save();
		return "index";
	}
	@InitBinder
	public void binder(WebDataBinder binder){
		//binder.setDisallowedFields(new String[]{"studentMobile"});
	}
	
	@RequestMapping(value="/admision", method = RequestMethod.GET)
	public String hello(@PathVariable Map<String,String> val,ModelMap model){
		
		RestTemplate rest=new RestTemplate();
		

		//		URL obj=new URL(url);
//		HttpURLConnection con=(HttpURLConnection) obj.openConnection();
//		int code=con.getResponseCode();
//		System.out.println(code);
//		
//		BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
//		System.out.println(br.readLine());
		
//*******************GET*************************
//		String url="http://localhost:8080/SpringMVC-Gontus/students";
//		ResponseEntity<Student> stu=rest.getForEntity(url,Student.class);
//		Student st=stu.getBody();
//		System.out.println(st.getStudentName());
		
//**********************POST***********************	
		
//		String url="http://localhost:8080/SpringMVC-Gontus/studentpost/{id}";
//		Student tmp=new Student();
//		tmp.setStudentName("hardik");
//		tmp.setStudentHobby("coding");
//		
//		Map<String,String> map=new HashMap<>();
//		map.put("id", "1");
//		System.out.println("yes");
//		
//		Student stu=rest.postForObject(url,tmp ,Student.class,map);
//		System.out.println(stu.getStudentName());
		
//********************DELETE****************************
//		String url="http://localhost:8080/SpringMVC-Gontus/studentdelete/{id}";
//		Map<String,String> map=new HashMap<>();
//		map.put("id","1");
//		rest.delete(url,map);
//********************PUT*******************************
		
//		String url="http://localhost:8080/SpringMVC-Gontus/studentput";
//		Student st=new Student();
//		
//		st.setStudentName("hardik-put");
//		st.setStudentHobby("song");
//		rest.put(url, st);
//		
		/*String ex="NULL_POINTER";
		
		if(ex.equalsIgnoreCase("NULL_POINTER")){
			//throw new NullPointerException("null pointer");
		}
		*/
		model.addAttribute("name", "hardik");
		return "redirect:/admision.jsp";
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
        email.setFrom("kakadiyahardik1996@gmail.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        
        // sends the e-mail
        mailSender.send(email);
        
        // forwards to the view named "Result"
        return "mail";
    }
	//****************************************************************
	
	
}
