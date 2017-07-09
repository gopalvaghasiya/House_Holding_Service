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
public class ServicesHelper {

	// *************************************Customer*************************************//
	// send otp to user
	public String sendOTP() throws URISyntaxException {
		final String ACCOUNT_SID = "ACcb68b0c54d089d3a56e17bd44b252a28";
		final String AUTH_TOKEN = "2ea5a9aba4a6904bf9b4a0c707fb3fa6";

		Random r = new Random();
		String otp = "";

		for (int i = 0; i < 4; i++) {
			otp += r.nextInt(9);
		}
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber("+917600338175"), // to
				new PhoneNumber("+14433992825"), // from
				otp).create();

		return otp;
	}

	// customer login
	public Customer validateAndLogin(long phone, String pass) {
		return null;
	}

	// check customer is regitered or not
	@Value("${customer.isregistered}")
	private String custIsRegistered;

	public int isCustomerRegistered(String phone) {

		RestTemplate rest = new RestTemplate();

		HashMap<String, String> hm = new HashMap<>();
		hm.put("phone", phone);

		return rest.getForEntity(custIsRegistered, Integer.class, hm).getBody();
	}

	@Value("${customer.login}")
	private String custLogin;

	// customer lgin
	public Customer isValidCust(String phone, String password) {
		RestTemplate res = new RestTemplate();

		HashMap<String, String> hm = new HashMap<>();
		hm.put("phone", phone);
		hm.put("password", password);

		return res.getForEntity(custLogin, Customer.class, hm).getBody();
	}

// ***************************Service Provider********************************//
	
	// check service provider is registered or not
	@Value("${serviceprovider.isregistered}")
	private String serproIsRegistered;

	public int isServiceProviderRegistered(String phone) {

		RestTemplate rest = new RestTemplate();

		HashMap<String, String> hm = new HashMap<>();
		hm.put("phone", phone);

		return rest.getForEntity(serproIsRegistered, Integer.class, hm).getBody();
	}
	
	// select service provider skill
	@Value("${serviceprovider.select.skill}")
	private String serproSkill;

	public ServicesJCategory[] selectServiceProviderSkill(int spi) {

		RestTemplate rest = new RestTemplate();

		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("spi", spi);

		return rest.getForEntity(serproSkill, ServicesJCategory[].class, hm).getBody();
	}
	
	// select service provider skill
	@Value("${serviceprovider.delete.skill}")
	private String serproDelSkill;

	public void deleteSerProSkill(int skill_id) {

		RestTemplate rest = new RestTemplate();

		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("skill_id", skill_id);

		rest.delete(serproDelSkill, hm);
	}
	
	// select service provider by service id
	@Value("${serviceprovider.select.byserviceid}")
	private String selSerProByServiceId;

	public ServiceProvider[] selectServiceProviderByServiceId(int service_id) {

		RestTemplate rest = new RestTemplate();

		HashMap<String, Integer> hm = new HashMap<>();
		hm.put("service_id", service_id);
		
		return rest.getForEntity(selSerProByServiceId,ServiceProvider[].class,hm).getBody();
	}
	
	// *************************Insert Data*****************************//

	// customer registration
	@Value("${customer.insert}")
	private String insCust;

	public int registration(Customer customer) {
		RestTemplate rest = new RestTemplate();

		return rest.postForObject(insCust, customer, Integer.class);
	}
	
	// service provider registration
	@Value("${serviceprovider.insert}")
	private String insSerPro;

	public int registration(ServiceProvider serpro) {
		RestTemplate rest = new RestTemplate();

		return rest.postForObject(insSerPro, serpro, Integer.class);
	}
	
	// add service provider skill return 0 if services already added other wise 1
	@Value("${serviceprovider.insert.skill}")
	private String insSerproSkill;

	public int insertSerProSkill(Skill skill) {
		RestTemplate rest = new RestTemplate();

		return rest.postForObject(insSerproSkill, skill, Integer.class);
	}	
	
	// service provider login
	@Value("${serviceprovider.login}")
	private String serproLogin;
	
	public ServiceProvider isValidSerPro(String phone, String password) {
		RestTemplate res = new RestTemplate();

		HashMap<String, String> hm = new HashMap<>();
		hm.put("phone", phone);
		hm.put("password", password);

		return res.getForEntity(serproLogin, ServiceProvider.class, hm).getBody();
	}	

	// ************************GET DATA*********************************//

	// get cities
	@Value("${selectall.city}")
	private String selectAllCity;

	public City[] getCitys() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<City[]> getCitys = restTemplate.getForEntity(selectAllCity, City[].class);
		City[] citys = getCitys.getBody();

		return citys;
	}

	// get service categories
	@Value("${selectall.servicecategory}")
	private String selectAllServiceCate;

	public ServiceCategory[] getServiceCategory() {
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForEntity(selectAllServiceCate, ServiceCategory[].class).getBody();
	}

	// *************************Select data by id****************************//

	@Value("${select.services.by.cateid}")
	private String serviceByCateId;

	public Services[] getServicesByCategoryId(int cateid) {
		RestTemplate rest = new RestTemplate();
		HashMap<String, Integer> hm = new HashMap<>();

		hm.put("cateid", cateid);
		return rest.getForEntity(serviceByCateId, Services[].class, hm).getBody();
	}

}
