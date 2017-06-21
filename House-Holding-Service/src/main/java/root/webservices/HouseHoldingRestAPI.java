package root.webservices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import root.dao.ServiceDAO;
import root.model.Admin;

@RestController
public class HouseHoldingRestAPI {
	
	@Autowired
	ServiceDAO dataAccess;
	
	//********************Rest API***********************************//
	
	//For Admin Login check user is valid or not
	@RequestMapping(value="/admin_login/{userName}/{password}",method=RequestMethod.GET)
	public ResponseEntity<Admin> getLoginAccessForAdmin(@PathVariable("userName")String userName,@PathVariable("password")String password){
		
		Admin admin=dataAccess.isValidAdmin(userName, password);
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
	}
	
	
}
