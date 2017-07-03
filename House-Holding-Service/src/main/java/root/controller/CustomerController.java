package root.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import root.model.*;


@Controller
public class CustomerController {

	@Autowired
	Services service;

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
	public ModelAndView MoveToCustomerRegistration() {

		ModelAndView model = new ModelAndView("customer/register");

		model.addObject("citys", service.getCitys());

		return model;
	}

	// requestration of customer
	@RequestMapping(value = "customer/register", method = RequestMethod.POST)
	public ModelAndView RegisterationOfCustomer(@ModelAttribute("customer") Customer customer, HttpSession session) throws URISyntaxException {

		ModelAndView model = new ModelAndView("customer/register");
		String otp=service.sendOTP();
		
		session.setAttribute("otp",otp);
		session.setAttribute("temp_cust", customer);
		model.addObject("otp", "yes");

		return model;
	}

	// final requestration of customer
	@RequestMapping(value = "customer/registration", method = RequestMethod.POST)
	public ModelAndView finalRegisterationOfCustomer(@RequestParam String otp, HttpSession session) {

		ModelAndView model;

		String sotp=(String)session.getAttribute("otp");
		
		if(sotp.equals(otp)){
			model = new ModelAndView("redirect:/customer");
		}
		else{
			model = new ModelAndView("customer/register");
			model.addObject("otp", "yes");
			model.addObject("response","Please Enter correct OTP");
		}
		//session.setAttribute("temp_cust", customer);
		model.addObject("otp", "yes");

		return model;
	}
}
