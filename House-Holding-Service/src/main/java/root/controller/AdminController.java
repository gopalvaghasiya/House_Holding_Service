package root.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import root.model.Admin;

@Controller
public class AdminController {

	@RequestMapping(value="admin/login",method=RequestMethod.GET)
	public String requestLoginPage(){
		return "admin/login";
	}
	
	@RequestMapping(value="admin/login",method=RequestMethod.POST)
	public void validateLogin(@ModelAttribute("admin") Admin admin ){
		RestTemplate restTemplate=new RestTemplate();
		
		String url="http://localhost:8080/admin_login/{userName}/{password}";
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("userName",admin.getUserName());
		map.put("password",admin.getPassword());
		
		ResponseEntity<Admin> data=restTemplate.getForEntity(url,Admin.class, map);
		
		admin=data.getBody();
		System.out.println(admin.getUserName()+" "+admin.getId());
		//return "admin/login";
	}
	
	@RequestMapping(value="admin/home",method=RequestMethod.GET)
	public ModelAndView moveToIndex(){
		
		ModelAndView model=new ModelAndView("admin/home");
		
		model.addObject("name","Kakadiya Hardik-IT ");
		
		return model;
	}
	
	@RequestMapping(value="admin/customer",method=RequestMethod.GET)
	public String moveTocustomer(){
		return "admin/customer";
	}
	
	@RequestMapping(value="admin/service_provider",method=RequestMethod.GET)
	public String moveToserviceprovider(){
		return "admin/service_provider";
	}
	
	@RequestMapping(value="admin/service_categories",method=RequestMethod.GET)
	public String moveToservice_category(){
		return "admin/service_categories";
	}
	
	@RequestMapping(value="admin/services",method=RequestMethod.GET)
	public String moveToservices(){
		return "admin/services";
	}
	
	@RequestMapping(value="admin/add_service_category",method=RequestMethod.GET)
	public String moveToasdd_service_category(){
		return "admin/add_service_category";
	}
	
	@RequestMapping(value="admin/add_service",method=RequestMethod.GET)
	public String moveToasdd_service(){
		return "admin/add_service";
	}
}
