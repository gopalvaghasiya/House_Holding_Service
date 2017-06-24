package root.testing;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import root.dao.ServiceDAO;
import root.model.Admin;
import root.model.AreaCity;
import root.model.AreaCity;

//@Controller
public class TestingController {
	
//	@Autowired
//	ServiceDAO s;
//	
//	@RequestMapping(value="admin/demo",method=RequestMethod.GET)
//	public void moveTocustomer(){
//		RestTemplate restTemplate = new RestTemplate();
//		String url = "http://localhost:8080/rest_select_areacitydemo";
//		Demo[] d=restTemplate.getForEntity(url, Demo[].class).getBody();
//		
//		for(Demo t:d){
//			System.out.println(t.getArea().getAreaId());
//			System.out.println(t.getCity().getCityName());
//			System.out.println(t.getArea().getAreaName());
//		}
//	}

	//	@Autowired
//	ServiceDAO s;
//	
//	@RequestMapping(value="/index",method=RequestMethod.GET)
//	public String moveToIndex(){
//		Admin admin=s.isValidAdmin("hardik", "hardik");
//		System.out.println(admin);
//		
//		if(admin!=null)
//			System.out.println(admin.getUserName());
//		
//		return "admin/login";
//	}
	
//	@RequestMapping(value="admin/customer",method=RequestMethod.GET)
//	public String moveTocustomer(){
//		return "customer";
//	}
//	
//	@RequestMapping(value="admin/service_provider",method=RequestMethod.GET)
//	public String moveToserviceprovider(){
//		return "service_provider";
//	}
//	
//	@RequestMapping(value="admin/service_categories",method=RequestMethod.GET)
//	public String moveToservice_category(){
//		return "service_categories";
//	}
//	
//	@RequestMapping(value="admin/services",method=RequestMethod.GET)
//	public String moveToservices(){
//		return "services";
//	}
//	
//	@RequestMapping(value="admin/add_service_category",method=RequestMethod.GET)
//	public String moveToasdd_service_category(){
//		return "add_service_category";
//	}
//	
//	@RequestMapping(value="admin/add_service",method=RequestMethod.GET)
//	public String moveToasdd_service(){
//		return "add_service";
//	}
}
