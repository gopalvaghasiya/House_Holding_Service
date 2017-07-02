package root.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

	@Value("${demo}")
	private String demo;

	// request for customer home page
	@RequestMapping(value = "customer", method = RequestMethod.GET)
	public String demo() {

		return "customer/home";
	}

	// request for customer login page
	@RequestMapping(value = "customer/login", method = RequestMethod.GET)
	public String MoveToCustomerLogin() {

		return "customer/login";
	}

	// request for customer Registration page
	@RequestMapping(value = "customer/register", method = RequestMethod.GET)
	public String MoveToCustomerRegistration() {

		return "customer/register";
	}
}
