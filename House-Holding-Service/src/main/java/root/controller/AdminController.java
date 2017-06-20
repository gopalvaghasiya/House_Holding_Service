package root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String moveToIndex(){
		//s.save();
		return "index";
	}
	
	@RequestMapping(value="admin/customer",method=RequestMethod.GET)
	public String moveTocustomer(){
		return "customer";
	}
	
	@RequestMapping(value="admin/service_provider",method=RequestMethod.GET)
	public String moveToserviceprovider(){
		return "service_provider";
	}
	
	@RequestMapping(value="admin/service_categories",method=RequestMethod.GET)
	public String moveToservice_category(){
		return "service_categories";
	}
	
	@RequestMapping(value="admin/services",method=RequestMethod.GET)
	public String moveToservices(){
		return "services";
	}
	
	@RequestMapping(value="admin/add_service_category",method=RequestMethod.GET)
	public String moveToasdd_service_category(){
		return "add_service_category";
	}
	
	@RequestMapping(value="admin/add_service",method=RequestMethod.GET)
	public String moveToasdd_service(){
		return "add_service";
	}
}
