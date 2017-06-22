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
	
	//********************Rest API***********************************//
	
	//For Admin Login check user is valid or not
	@RequestMapping(value="/rest_admin_login/{userName}/{password}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> getLoginAccessForAdmin(@PathVariable("userName")String userName,@PathVariable("password")String password){
		
		Admin admin=dataAccess.isValidAdmin(userName, password);
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
	}
	
	//insert city
	@RequestMapping(value="/rest_insert_city",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Integer insert_city(@RequestBody City city){
		
		return dataAccess.insertCity(city);
	}
	
	//insert area
	@RequestMapping(value="/rest_insert_area",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Integer insert_area(@RequestBody Area area){
		return dataAccess.insertArea(area);
	}
	
	//select all city
	@RequestMapping(value="/rest_select_all_city",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<City>> sellectAllCity(){
		
		ArrayList<City> citys=dataAccess.selectCity();
		return new ResponseEntity<ArrayList<City>>(citys,HttpStatus.OK);
	}
}
