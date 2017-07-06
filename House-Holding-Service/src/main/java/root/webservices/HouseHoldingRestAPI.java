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

	private static final String[] APPLICATION_JSON_VALUE = null;
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

	// check is customer mobile no is registered
	@RequestMapping(value = "/rest_customer_isregistered/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> isCustomerRegistered(@PathVariable("phone") String phone) {
		return new ResponseEntity<Integer>(dataAccess.isCustomerRegistered(phone), HttpStatus.OK);
	}

	// check is service provider mobile no is registered
	@RequestMapping(value = "/rest_serviceprovider_isregistered/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> isServiceProviderRegistered(@PathVariable("phone") String phone) {
		return new ResponseEntity<Integer>(dataAccess.isServiceProviderRegistered(phone), HttpStatus.OK);
	}

	// customer login
	@RequestMapping(value = "/rest_customer_login/{phone}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getLoginAccessForCustomer(@PathVariable("phone") String phone,
			@PathVariable("password") String password) {

		return new ResponseEntity<Customer>(dataAccess.isValidCustomer(phone, password), HttpStatus.OK);
	}
	
	// service provider login
	@RequestMapping(value="/rest_serviceprovider_login/{phone}/{password}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceProvider> getLogginAccessForServiceProvider(@PathVariable("phone") String phone,
			@PathVariable("password")String password){
		return new ResponseEntity<ServiceProvider>(dataAccess.isValidServiceprovider(phone, password),HttpStatus.OK);
	}

	// *************************Insert
	// Data*************************************//

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

	// insert customer details
	@RequestMapping(value = "/rest_insert_customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer insertCustomer(@RequestBody Customer customer) {
		return dataAccess.insertCustomerDetail(customer);
	}
	
	// insert service provider details
	@RequestMapping(value = "/rest_insert_serviceprovider", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer insertServiceProvider(@RequestBody ServiceProvider serpro) {
		return dataAccess.insertServiceProviderDetail(serpro);
	}	

	// **************************Update***********************************************
	// Update area
	@RequestMapping(value = "/rest_update_area", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer updateArea(@RequestBody Area area) {
		return dataAccess.updateArea(area);
	}

	// Update area
	@RequestMapping(value = "/rest_update_city", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer updateCity(@RequestBody City city) {
		return dataAccess.updateCity(city);
	}

	// Update services
	@RequestMapping(value = "/rest_update_services", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer updateServices(@RequestBody Services service) {
		return dataAccess.updateServices(service);
	}

	// Update service category
	@RequestMapping(value = "/rest_update_service_category", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer updateServices(@RequestBody ServiceCategory sercate) {
		return dataAccess.updateServiceCategory(sercate);
	}

	// ***********************PUT
	// Request************************************************
	// update user status
	@RequestMapping(value = "/rest_update_user_status/{user_type}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUserStatus(@PathVariable("user_type") int user_type, @RequestBody Customer c) {
		dataAccess.updateUserStatus(user_type, c);
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

	// *****************************************Select by
	// id*****************************/

	// select area by area id
	@RequestMapping(value = "rest_select_area_by_id/{area_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Area> selectAreById(@PathVariable("area_id") int area_id) {
		Area a = dataAccess.selectArea(area_id);

		if (a != null)
			return new ResponseEntity<Area>(a, HttpStatus.OK);

		return new ResponseEntity<Area>(a, HttpStatus.NOT_FOUND);
	}

	// select City by city id
	@RequestMapping(value = "rest_select_city_by_id/{city_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> selectCityById(@PathVariable("city_id") int city_id) {
		return new ResponseEntity<City>(dataAccess.selectCity(city_id), HttpStatus.OK);
	}

	// select Service by id
	@RequestMapping(value = "rest_select_service_by_id/{service_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Services> selectServiceById(@PathVariable("service_id") int service_id) {
		return new ResponseEntity<Services>(dataAccess.selectService(service_id), HttpStatus.OK);
	}

	// select services by category id
	@RequestMapping(value = "rest_select_services_by_cate_id/{cateid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Services>> selectServicesByCateId(@PathVariable("cateid") int cateid) {
		return new ResponseEntity<ArrayList<Services>>(dataAccess.selectServiceByCategoryId(cateid), HttpStatus.OK);
	}

	// select Service category by id
	@RequestMapping(value = "rest_select_service_category_by_id/{sercate_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceCategory> selectServiceCategoryById(@PathVariable("sercate_id") int sercate_id) {

		ServiceCategory sc = dataAccess.selectServiceCategory(sercate_id);

		if (sc != null)
			return new ResponseEntity<ServiceCategory>(sc, HttpStatus.OK);

		return new ResponseEntity<ServiceCategory>(sc, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "rest_select_area_by_city_id/{cityId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Area>> selectAreaByCityId(@PathVariable("cityId") int cityId) {
		return new ResponseEntity<ArrayList<Area>>(dataAccess.selectAreaByCityId(cityId), HttpStatus.OK);
	}

	// ****************************************DELETE*********************************//

	// delete area by id
	@RequestMapping(value = "rest_delete_area_by_id/{area_id}", method = RequestMethod.DELETE)
	public Integer deleteArea(@PathVariable("area_id") int area_id) {
		return dataAccess.deleteArea(area_id);
	}

	// delete city by id
	@RequestMapping(value = "rest_delete_city_by_id/{city_id}", method = RequestMethod.DELETE)
	public Integer deleteCity(@PathVariable("city_id") int city_id) {
		return dataAccess.deleteCity(city_id);
	}

	// delete Service by id
	@RequestMapping(value = "rest_delete_service_by_id/{service_id}", method = RequestMethod.DELETE)
	public Integer deleteService(@PathVariable("service_id") int service_id) {
		return dataAccess.deleteService(service_id);
	}

	// delete Service category by id
	@RequestMapping(value = "rest_delete_service_category_by_id/{cate_id}", method = RequestMethod.DELETE)
	public Integer deleteServiceCategory(@PathVariable("cate_id") int cate_id) {
		return dataAccess.deleteServiceCategory(cate_id);
	}
}
