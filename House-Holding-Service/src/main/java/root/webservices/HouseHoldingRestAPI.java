package root.webservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import root.dao.ServiceDAO;
import root.model.*;

@RestController
public class HouseHoldingRestAPI {

	@Autowired
	ServiceDAO dataAccess;

	// ********************Rest API***********************************//

	// For Admin Login check user is valid or not
	@RequestMapping(value = "/rest_admin_login/{userName}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> getLoginAccessForAdmin(@PathVariable("userName") String userName,
			@PathVariable("password") String password) {

		Admin admin = dataAccess.isValidAdmin(userName, password);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK);
	}

	// insert city
	@RequestMapping(value = "/rest_insert_city", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer insert_city(@RequestBody City city) {

		return dataAccess.insertCity(city);
	}

	// insert area
	@RequestMapping(value = "/rest_insert_area", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer insert_area(@RequestBody Area area) {
		return dataAccess.insertArea(area);
	}

	// insert service category
	@RequestMapping(value = "/rest_insert_service_category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer insert_Service_category(@RequestBody ServiceCategory serviceCategory) {
		return dataAccess.insertServiceCategory(serviceCategory);
	}

	// insert services
	@RequestMapping(value = "/rest_insert_services", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer insertServices(@RequestBody Services service) {
		return dataAccess.insertServices(service);
	}

	// select all city
	@RequestMapping(value = "/rest_select_all_city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<City>> sellectAllCity() {

		ArrayList<City> citys = dataAccess.selectCity();
		return new ResponseEntity<ArrayList<City>>(citys, HttpStatus.OK);
	}

	// select all Service Category
	@RequestMapping(value = "/rest_select_all_service_category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ServiceCategory>> selsectAllServiceCategory() {
		return new ResponseEntity<ArrayList<ServiceCategory>>(dataAccess.selectServiceCategory(), HttpStatus.OK);
	}

	// select area join city
	@RequestMapping(value = "/rest_select_areacity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<AreaCity>> selectAllAreaCity() {
		return new ResponseEntity<ArrayList<AreaCity>>(dataAccess.selectAreaCity(), HttpStatus.OK);
	}

	// select services join services category
	@RequestMapping(value = "/rest_select_servicesjcategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ServicesJCategory>> selectAllServiceJCstegory() {
		return new ResponseEntity<ArrayList<ServicesJCategory>>(dataAccess.selectServicesJCategory(), HttpStatus.OK);
	}

	// select Customer
	@RequestMapping(value = "/rest_select_allcustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Customer>> selectAllCustomer() {
		return new ResponseEntity<ArrayList<Customer>>(dataAccess.selectAllCustomer(), HttpStatus.OK);
	}

	// select service provider
	@RequestMapping(value = "/rest_select_allserviceprovider", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ServiceProvider>> selectAllServiceProvider() {
		return new ResponseEntity<ArrayList<ServiceProvider>>(dataAccess.selectAllServiceProvider(), HttpStatus.OK);
	}
	
	// select area by area id
	@RequestMapping(value="rest_select_area_by_id",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Area> selectAreById(@PathVariable("area_id") int area_id){
		return new ResponseEntity<Area>(dataAccess.selectArea(area_id),HttpStatus.OK);
	}
}
