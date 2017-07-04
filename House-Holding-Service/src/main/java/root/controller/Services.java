package root.controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import root.model.*;

@Service
public class Services {

//*************************************Customer*************************************//	
	// send otp to user
	public String sendOTP()throws URISyntaxException {
		final String ACCOUNT_SID = "ACcb68b0c54d089d3a56e17bd44b252a28";
		final String AUTH_TOKEN = "2ea5a9aba4a6904bf9b4a0c707fb3fa6";

		Random r=new Random();
		String otp="";
		
		for(int i=0;i<4;i++){
			otp+=r.nextInt(9);
		}
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber("+917600338175"), // to
				new PhoneNumber("+14433992825"), // from
				otp).create();
		
		return otp;
	}

	// customer login
	public Customer validateAndLogin(long phone,String pass){
		return null;
	}
	
	// check customer is regitered or not
	@Value("${customer.isregitered}")
	private String custIsRegistered;
	
	public int isCustomerRegistered(String phone){
		
		RestTemplate rest=new RestTemplate();
		
		HashMap<String,String> hm=new HashMap<>();
		hm.put("phone",phone);
		
		return rest.getForEntity(custIsRegistered, Integer.class,hm).getBody();
	}
	
	@Value("${customer.login}")
	private String custLogin;
	
	// customer lgin
	public Customer isValidCust(String phone,String password){
		RestTemplate res=new RestTemplate();
		
		HashMap<String,String> hm=new HashMap<>();
		hm.put("phone",phone);
		hm.put("password",password);
		
		return res.getForEntity(custLogin,Customer.class,hm).getBody();
	}
	//*************************Insert Data*****************************//
	
	// customer registration
	@Value("${customer.insert}")
	private String insCust;
	
	public int registration(Customer customer){
		RestTemplate rest=new RestTemplate();
		
		return rest.postForObject(insCust,customer,Integer.class);
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
}
