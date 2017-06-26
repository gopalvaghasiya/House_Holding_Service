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

	// insert area to Db
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

	// ****************************EDIT
	// Update*************************************//

	// request edit area page
	@RequestMapping(value = "admin/edit_area", method = RequestMethod.GET)
	public ModelAndView editArea(@RequestParam int area_id, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/edit_area");
		model.addObject("citys", getCitys());
		model.addObject("area", getArea(area_id));

		return model;
	}

	// Update area
	@RequestMapping(value = "admin/update_area", method = RequestMethod.POST)
	public ModelAndView updateArea(@ModelAttribute("area") Area area, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/area");

		int res = updateArea(area);

		model.addObject("areas", getAreaCity());
		if (res == 1) {
			model.addObject("response", "Update Success fully");
		} else {
			model.addObject("response", "Update failed");
		}
		return model;
	}

	// request edit city page
	@RequestMapping(value = "admin/edit_city", method = RequestMethod.GET)
	public ModelAndView editCity(@RequestParam int city_id, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/edit_city");
		model.addObject("city", getCity(city_id));

		return model;
	}

	// Update City
	@RequestMapping(value = "admin/update_city", method = RequestMethod.POST)
	public ModelAndView updateCity(@ModelAttribute("city") City city, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/city");

		int res = updateCity(city);

		model.addObject("citys", getCitys());
		if (res == 1) {
			model.addObject("response", "Update Success fully");
		} else {
			model.addObject("response", "Update failed");
		}
		return model;
	}

	// request edit Service page
	@RequestMapping(value = "admin/edit_service", method = RequestMethod.GET)
	public ModelAndView editService(@RequestParam int service_id, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/edit_service");
		model.addObject("service", getService(service_id));
		model.addObject("categories", getServiceCategory());

		return model;
	}

	// Update Service
	@RequestMapping(value = "admin/update_service", method = RequestMethod.POST)
	public ModelAndView updateService(@ModelAttribute("service") Services service, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/services");

		int res = updateServices(service);

		model.addObject("services", getServicesJCategory());
		if (res == 1) {
			model.addObject("response", "Update Success fully");
		} else {
			model.addObject("response", "Update failed");
		}
		return model;
	}

	// request edit Service Category page
	@RequestMapping(value = "admin/edit_sercate", method = RequestMethod.GET)
	public ModelAndView editServiceCategory(@RequestParam int cate_id, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/edit_service_category");
		model.addObject("sercate", getServiceCategory(cate_id));

		return model;
	}

	// update servide category
	@RequestMapping(value = "admin/update_sercate", method = RequestMethod.POST)
	public ModelAndView updateServiceCategory(@RequestParam CommonsMultipartFile file,
			@ModelAttribute("sercate") ServiceCategory sercate, HttpSession session) throws IOException {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		model = new ModelAndView("admin/service_categories");

		// setting of image
		String folder = "/resources/images/service_category/";
		String path = session.getServletContext().getRealPath(folder);
		String fileName = file.getOriginalFilename();

		sercate.setCateImg(folder + fileName);

		byte[] bytes = file.getBytes();

		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(new File(path + File.separator + fileName)));
		bos.write(bytes);
		bos.flush();
		bos.close();
		// end

		int res = updateServiceCAtegory(sercate);

		model.addObject("categories", getServiceCategory());
		if (res == 1) {
			model.addObject("response", "Update Success fully");
		} else {
			model.addObject("response", "Update failed");
		}
		return model;
	}

	//update user status
	@RequestMapping(value = "admin/change_user_status", method = RequestMethod.GET)
	public ModelAndView changeUserStatus(@RequestParam int user_id,@RequestParam int status_id,@RequestParam int user_type, HttpSession session) {
		
		Customer c=new Customer();
		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}
		
		if(user_type==1)
			model = new ModelAndView("redirect:/admin/customer");
		else
			model = new ModelAndView("redirect:/admin/service_provider");
		
		status_id=status_id%2;
		status_id++;
		c.setCustomerId(user_id);
		c.setStatus(""+status_id);
		
		updateUserStatus(user_type, c);
		return model;
	}	
	
	// **********************************DELETE****************************//

	@RequestMapping(value = "admin/delete_area", method = RequestMethod.GET)
	public ModelAndView deleteArea(@RequestParam int area_id, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		// deleteArea(area_id);

		model = new ModelAndView("redirect:/admin/area");

		return model;
	}

	// delete city by id
	@RequestMapping(value = "admin/delete_city", method = RequestMethod.GET)
	public ModelAndView deleteCity(@RequestParam int city_id, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		deleteCity(city_id);

		model = new ModelAndView("redirect:/admin/city");

		return model;
	}

	// delete service by id
	@RequestMapping(value = "admin/delete_service", method = RequestMethod.GET)
	public ModelAndView deleteService(@RequestParam int service_id, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		deleteService(service_id);

		model = new ModelAndView("redirect:/admin/services");

		return model;
	}

	// delete service category by id
	@RequestMapping(value = "admin/delete_sercate", method = RequestMethod.GET)
	public ModelAndView deleteServiceCategory(@RequestParam int cate_id, HttpSession session) {

		ModelAndView model;
		if (!isLoggeding(session)) {
			model = new ModelAndView("admin/login");
			model.addObject("message", "Please login");
			return model;
		}

		deleteServiceCategory(cate_id);

		model = new ModelAndView("redirect:/admin/service_categories");

		return model;
	}

	// ************************GET DATA*********************************//

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

	// select all service provider
	public ServiceProvider[] getAllServiceProvider() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_select_allserviceprovider";

		return restTemplate.getForEntity(url, ServiceProvider[].class).getBody();
	}

	// **************get data by id********************

	// get area by id
	public Area getArea(int id) {
		RestTemplate restT = new RestTemplate();
		String url = "http://localhost:8080/rest_select_area_by_id/{area_id}";

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("area_id", id);
		return restT.getForEntity(url, Area.class, map).getBody();
	}

	// get city by id
	public City getCity(int id) {
		RestTemplate restT = new RestTemplate();
		String url = "http://localhost:8080/rest_select_city_by_id/{city_id}";

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("city_id", id);
		return restT.getForEntity(url, City.class, map).getBody();
	}

	// get Service by id
	public Services getService(int id) {
		RestTemplate restT = new RestTemplate();
		String url = "http://localhost:8080/rest_select_service_by_id/{service_id}";

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("service_id", id);
		return restT.getForEntity(url, Services.class, map).getBody();
	}

	// get Service category by id
	public ServiceCategory getServiceCategory(int id) {
		RestTemplate restT = new RestTemplate();
		String url = "http://localhost:8080/rest_select_service_category_by_id/{sercate_id}";

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sercate_id", id);
		return restT.getForEntity(url, ServiceCategory.class, map).getBody();
	}

	// ******************UPdate*******************************
	// update area
	public int updateArea(Area area) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_update_area";

		return restTemplate.postForObject(url, area, Integer.class);

	}

	// update City
	public int updateCity(City city) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_update_city";

		return restTemplate.postForObject(url, city, Integer.class);

	}

	// update Services
	public int updateServices(Services service) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_update_services";

		return restTemplate.postForObject(url, service, Integer.class);

	}

	// update Service category
	public int updateServiceCAtegory(ServiceCategory sercate) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest_update_service_category";

		return restTemplate.postForObject(url, sercate, Integer.class);

	}
	
	//update user status
	public void updateUserStatus(int user_type,Customer cust){
		RestTemplate rest=new RestTemplate();
		String url="http://localhost:8080//rest_update_user_status/{user_type}";

		Map<String,Integer> map=new HashMap<>();
		map.put("user_type", user_type);
		
		rest.put(url, cust, map);;
	}

	// ************************DELETE**********************************

	// delete area
	public void deleteArea(int area_id) {
		RestTemplate resT = new RestTemplate();
		String url = "http://localhost:8080/rest_delete_area_by_id/{area_id}";

		Map<String, Integer> map = new HashMap<>();
		map.put("area_id", area_id);

		resT.delete(url, map);
	}

	// delte city
	public void deleteCity(int city_id) {
		RestTemplate resT = new RestTemplate();
		String url = "http://localhost:8080/rest_delete_city_by_id/{city_id}";

		Map<String, Integer> map = new HashMap<>();
		map.put("city_id", city_id);

		resT.delete(url, map);
	}

	// delte service
	public void deleteService(int service_id) {
		RestTemplate resT = new RestTemplate();
		String url = "http://localhost:8080/rest_delete_service_by_id/{service_id}";

		Map<String, Integer> map = new HashMap<>();
		map.put("service_id", service_id);

		resT.delete(url, map);
	}

	// delte service category
	public void deleteServiceCategory(int cate_id) {
		RestTemplate resT = new RestTemplate();
		String url = "http://localhost:8080/rest_delete_service_category_by_id/{cate_id}";

		Map<String, Integer> map = new HashMap<>();
		map.put("cate_id", cate_id);

		resT.delete(url, map);
	}
}
