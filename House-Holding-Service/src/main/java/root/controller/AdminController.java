package root.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import root.model.Admin;
import root.model.Area;
import root.model.City;

@Controller
public class AdminController {

	//check user is loged in or not
	public boolean isLoggeding(HttpSession session){
		
		String user_role=(String)session.getAttribute("user_role");
		if(user_role!=null && user_role.equals("admin")){
			return true;
		}
		else{
			return false;
		}
	}
	
	// request for login page
	@RequestMapping(value="admin",method=RequestMethod.GET)
	public String RequestForAdminWeb(){
		return "admin/login";
	}
	
	//request for login page
	@RequestMapping(value="admin/login",method=RequestMethod.GET)
	public String requestLoginPage(){
		return "admin/login";
	}
	
	// validate user valid or not and acording to result set session
	@RequestMapping(value="admin/login",method=RequestMethod.POST)
	public ModelAndView validateLogin(@ModelAttribute("admin") Admin admin,HttpSession session ){
		RestTemplate restTemplate=new RestTemplate();
		
		String url="http://localhost:8080/rest_admin_login/{userName}/{password}";
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("userName",admin.getUserName());
		map.put("password",admin.getPassword());
		
		ResponseEntity<Admin> data=restTemplate.getForEntity(url,Admin.class, map);
		
		admin=data.getBody();
		
		ModelAndView model;
		if(admin!=null){
			session.setAttribute("user",admin);
			session.setAttribute("user_role","admin");
			
			model=new ModelAndView("admin/home");
			return model;
		}
		else
		{
			model=new ModelAndView("admin/login");
			model.addObject("message","UserName or Passwrd is wrong");
			
			return model;
		}
	}
	
	//logout admin and request for login page
	@RequestMapping(value="admin/logout",method=RequestMethod.GET)
	public String logout(HttpSession  session){
		session.invalidate();
		
		return "admin/login";
	}
	
	// request for home page
	@RequestMapping(value="admin/home",method=RequestMethod.GET)
	public ModelAndView moveToHome(HttpSession session){
		
		ModelAndView model;
		
		if(!isLoggeding(session)){
			model=new ModelAndView("admin/login");
			model.addObject("message","Please login");
			return model;
		}
		
		model=new ModelAndView("admin/home");
		
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
	
	@RequestMapping(value="admin/city",method=RequestMethod.GET)
	public String moveToCity(){
		return "admin/city";
	}
	
	@RequestMapping(value="admin/area",method=RequestMethod.GET)
	public String moveToarea(){
		return "admin/area";
	}
	
	@RequestMapping(value="admin/add_service_category",method=RequestMethod.GET)
	public String moveToasdd_service_category(){
		return "admin/add_service_category";
	}
	
	@RequestMapping(value="admin/add_service",method=RequestMethod.GET)
	public String moveToasdd_service(){
		return "admin/add_service";
	}
	
	//request for add_city page
	@RequestMapping(value="admin/add_city",method=RequestMethod.GET)
	public ModelAndView moveToasdd_city(HttpSession session){
		
		ModelAndView model;
		
		if(!isLoggeding(session)){
			model=new ModelAndView("admin/login");
			model.addObject("message","Please login");
			return model;
		}
		model=new ModelAndView("admin/add_city");
		
		return model;
	}
	
	//insert city to DB
	@RequestMapping(value="admin/add_city",method=RequestMethod.POST)
	public ModelAndView insertCityToDb(@ModelAttribute("city") City city,HttpSession session){
		
		ModelAndView model;
		if(!isLoggeding(session)){
			model=new ModelAndView("admin/login");
			model.addObject("message","Please login");
			return model;
		}
		
		RestTemplate restTemplate=new RestTemplate();
		String url="http://localhost:8080/rest_insert_city";
		
		int i=restTemplate.postForObject(url, city, Integer.class);
		
		model=new ModelAndView("admin/add_city");
		
		if(i==1){
			model.addObject("response", "Successfully inserted");
		}
		else{
			model.addObject("response", "fail please try again");
		}
		return model;
	}
	
	@RequestMapping(value="admin/add_area",method=RequestMethod.GET)
	public ModelAndView moveToasdd_area(HttpSession session){
		
		ModelAndView model;
		if(!isLoggeding(session)){
			model=new ModelAndView("admin/login");
			model.addObject("message","Please login");
			return model;
		}
		model=new ModelAndView("admin/add_area");
		
		City[] citys=getCitys();
		model.addObject("citys",citys);
		
		return model;
	}
	
	@RequestMapping(value="admin/add_area",method=RequestMethod.POST)
	public ModelAndView insertAreaToDb(@ModelAttribute("area")Area area,HttpSession session){
		
		ModelAndView model;
		if(!isLoggeding(session)){
			model=new ModelAndView("admin/login");
			model.addObject("message","Please login");
			return model;
		}
		model=new ModelAndView("admin/add_area");
		
		RestTemplate restTemplate=new RestTemplate();
		String url="http://localhost:8080/rest_insert_area";
		
		int res=restTemplate.postForObject(url, area, Integer.class);
		
		model.addObject("citys", getCitys());
		if(res==1){
			model.addObject("response", "Successfully inserted");
		}
		else{
			model.addObject("response", "fail please try again");
		}
		return model;
	}
	
	public City[] getCitys(){
		RestTemplate restTemplate=new RestTemplate();
		String url="http://localhost:8080/rest_select_all_city";
		
		ResponseEntity<City[]> getCitys=restTemplate.getForEntity(url,City[].class);
		City[] citys=getCitys.getBody();
		
		return citys;
	}
}
