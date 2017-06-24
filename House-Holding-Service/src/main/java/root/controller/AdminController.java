package root.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import root.model.*;

@Controller
public class AdminController {

	// check user is loged in or not
	public boolean isLoggeding(HttpSession session) {

		String user_role = (String) session.getAttribute("user_role");
		if (user_role != null && user_role.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	// request for login page
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String RequestForAdminWeb() {
		return "admin/login";
	}

	// request for login page
	@RequestMapping(value = "admin/login", method = RequestMethod.GET)
	public String requestLoginPage() {
		return "admin/login";
	}

	// validate user valid or not and acording to result set session
	@RequestMapping(value = "admin/login", method = RequestMethod.POST)
	public ModelAndView validateLogin(@ModelAttribute("admin") Admin admin, HttpSession session) {
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8080/rest_admin_login/{userName}/{password}";

		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", admin.getUserName());
		map.put("password", admin.getPassword());

		ResponseEntity<Admin> data = restTemplate.getForEntity(url, Admin.class, map);

		admin = data.getBody();

		ModelAndView model;
		if (admin != null) {
			session.setAttribute("user", admin);
			session.setAttribute("user_role", "admin");

			model = new ModelAndView("admin/home");
			return model;
		} else {
			model = new ModelAndView("admin/login");
			model.addObject("message", "UserName or Passwrd is wrong");

			return model;
		}
	}

	// logout admin and request for login page
	@RequestMapping(value = "admin/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();

		return "admin/login";
	}

	// request for home page
	@RequestMapping(value = "admin/home", method = RequestMethod.GET)
	public ModelAndView moveToHome(HttpSession session) {

		ModelAndView model;

		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		model = new ModelAndView("admin/home");

		return model;
	}

	// request for view customer page
	@RequestMapping(value = "admin/customer", method = RequestMethod.GET)
	public ModelAndView moveTocustomer(HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		model = new ModelAndView("admin/customer");
		model.addObject("customers", getAllCustomer());
		return model;
	}

	// request for view service provider
	@RequestMapping(value = "admin/service_provider", method = RequestMethod.GET)
	public ModelAndView moveToserviceprovider(HttpSession session) {
		
		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		model = new ModelAndView("admin/service_provider");
		model.addObject("providers", getAllServiceProvider());

		return model;
	}

	// request for view Service category page
	@RequestMapping(value = "admin/service_categories", method = RequestMethod.GET)
	public ModelAndView moveToservice_category(HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		model = new ModelAndView("admin/service_categories");
		model.addObject("categories", getServiceCategory());

		return model;
	}

	// request for view services page
	@RequestMapping(value = "admin/services", method = RequestMethod.GET)
	public ModelAndView moveToservices(HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/services");
		model.addObject("services", getServicesJCategory());

		return model;
	}

	// request for view city page
	@RequestMapping(value = "admin/city", method = RequestMethod.GET)
	public ModelAndView moveToCity(HttpSession session) {
		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		model = new ModelAndView("admin/city");
		model.addObject("citys", getCitys());

		return model;
	}

	// request for view area page
	@RequestMapping(value = "admin/area", method = RequestMethod.GET)
	public ModelAndView moveToarea(HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/area");
		model.addObject("areas", getAreaCity());
		return model;
	}

	// Request for add service category page
	@RequestMapping(value = "admin/add_service_category", method = RequestMethod.GET)
	public ModelAndView moveToadd_service_category(HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		model = new ModelAndView("admin/add_service_category");
		return model;
	}

	// add service category to data base
	@RequestMapping(value = "admin/add_service_category", method = RequestMethod.POST)
	public ModelAndView insertServiceCategoryToDb(@RequestParam CommonsMultipartFile file,
			@ModelAttribute("serviceCategory") ServiceCategory serviceCategory, HttpSession session)
					throws IOException {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/add_service_category");

		String folder = "/resources/images/service_category/";
		String path = session.getServletContext().getRealPath(folder);
		String fileName = file.getOriginalFilename();

		serviceCategory.setCateImg(folder + fileName);

		byte[] bytes = file.getBytes();

		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(new File(path + File.separator + fileName)));
		bos.write(bytes);
		bos.flush();
		bos.close();

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_insert_service_category";
		int i = restTemplate.postForObject(url, serviceCategory, Integer.class);

		if (i == 1) {
			model.addObject("response", "inserted success fully");
		} else {
			model.addObject("response", "insersion failed");
		}
		return model;
	}

	// Request for add service page
	@RequestMapping(value = "admin/add_service", method = RequestMethod.GET)
	public ModelAndView moveToasdd_service(HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/add_service");

		model.addObject("categories", getServiceCategory());

		return model;
	}

	// insert service to db
	@RequestMapping(value = "admin/add_service", method = RequestMethod.POST)
	public ModelAndView insertServiceTODb(@ModelAttribute("service") Services service, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/add_service");

		String url = "http://localhost:8080/rest_insert_services";
		RestTemplate resTemp = new RestTemplate();

		int i = resTemp.postForObject(url, service, Integer.class);

		if (i == 1) {
			model.addObject("response", "inserted success fully");
		} else {
			model.addObject("response", "insersion failed");
		}
		model.addObject("categories", getServiceCategory());

		return model;
	}

	// request for add_city page
	@RequestMapping(value = "admin/add_city", method = RequestMethod.GET)
	public ModelAndView moveToasdd_city(HttpSession session) {

		ModelAndView model;

		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/add_city");

		return model;
	}

	// insert city to DB
	@RequestMapping(value = "admin/add_city", method = RequestMethod.POST)
	public ModelAndView insertCityToDb(@ModelAttribute("city") City city, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_insert_city";

		int i = restTemplate.postForObject(url, city, Integer.class);

		model = new ModelAndView("admin/add_city");

		if (i == 1) {
			model.addObject("response", "Successfully inserted");
		} else {
			model.addObject("response", "fail please try again");
		}
		return model;
	}

	// request for add area page
	@RequestMapping(value = "admin/add_area", method = RequestMethod.GET)
	public ModelAndView moveToasdd_area(HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/add_area");

		City[] citys = getCitys();
		model.addObject("citys", citys);

		return model;
	}

	// insert are to Db
	@RequestMapping(value = "admin/add_area", method = RequestMethod.POST)
	public ModelAndView insertAreaToDb(@ModelAttribute("area") Area area, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/add_area");

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_insert_area";

		int res = restTemplate.postForObject(url, area, Integer.class);

		model.addObject("citys", getCitys());
		if (res == 1) {
			model.addObject("response", "Successfully inserted");
		} else {
			model.addObject("response", "fail please try again");
		}
		return model;
	}

//****************************EDIT Update*************************************//
	
	// edit area
	@RequestMapping(value="admin/edit_area",method=RequestMethod.GET)
	public ModelAndView editArea(@RequestParam int area_id,HttpSession session){
		
		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model=new ModelAndView("admin/city");
		
		return model; 
	}

	
//************************GET DATA*********************************//
	
	// get cities
	public City[] getCitys() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_select_all_city";

		ResponseEntity<City[]> getCitys = restTemplate.getForEntity(url, City[].class);
		City[] citys = getCitys.getBody();

		return citys;
	}

	// get service cetegories
	public ServiceCategory[] getServiceCategory() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_select_all_service_category";

		return restTemplate.getForEntity(url, ServiceCategory[].class).getBody();
	}

	// get area join city
	public AreaCity[] getAreaCity() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_select_areacity";

		return restTemplate.getForEntity(url, AreaCity[].class).getBody();
	}
	
	// get area
	public Area getArea(int id){
		
	}

	// get services join Category
	public ServicesJCategory[] getServicesJCategory() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_select_servicesjcategory";

		return restTemplate.getForEntity(url, ServicesJCategory[].class).getBody();
	}

	// select all customer
	public Customer[] getAllCustomer() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_select_allcustomer";

		return restTemplate.getForEntity(url, Customer[].class).getBody();
	}

	// select all customer
	public ServiceProvider[] getAllServiceProvider() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_select_allserviceprovider";

		return restTemplate.getForEntity(url, ServiceProvider[].class).getBody();
	}
}
