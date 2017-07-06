package root.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import root.model.*;

@Controller
public class ServiceProviderController {

	@Autowired
	ServicesHelper service;
	
	//check service provider is logged in or not
	public boolean isLogedin(HttpSession session){
	
		String user_role=(String)session.getAttribute("user_role");
		if(user_role!=null && user_role.equals("serviceprovider")){
			return true;
		}
		else{
			return false;
		}
	}
	
	//request for home page
	@RequestMapping(value="serviceprovider/home",method=RequestMethod.GET)
	public ModelAndView moveToHomePage(HttpSession session){
		
		ModelAndView model;
		
		if(!isLogedin(session)){
			model=new ModelAndView("serviceprovider/login");
			model.addObject("msg","Pleasse Login");
			return model;
		}
		
		model=new ModelAndView("serviceprovider/home");
		
		return model;
	}
	// request for access web
	@RequestMapping(value = "serviceprovider", method = RequestMethod.GET)
	public ModelAndView goToHomePage() {

		ModelAndView model = new ModelAndView("serviceprovider/login");
		return model;
	}

	// request for registration page
	@RequestMapping(value = "serviceprovider/register", method = RequestMethod.GET)
	public ModelAndView MoveToServiceProviderRegistration() {

		ModelAndView model = new ModelAndView("serviceprovider/register");

		model.addObject("citys", service.getCitys());

		return model;
	}

	// request registration of service provider
	@RequestMapping(value = "serviceprovider/register", method = RequestMethod.POST)
	public ModelAndView RegisterationOfServiceProvider(@ModelAttribute("serpro") @Valid ServiceProvider serpro,
			BindingResult result, HttpSession session) throws URISyntaxException {

		ModelAndView model;

		if (result.hasErrors()) {
			model = new ModelAndView("redirect:/serviceprovider/register");
			return model;
		}
		if (service.isServiceProviderRegistered(serpro.getMobileNo()) == 1) {
			model = new ModelAndView("redirect:/serviceprovider/register");
			model.addObject("otp", "no");
			model.addObject("response", "mobile no is registered");
			return model;
		}
		model = new ModelAndView("serviceprovider/register");
		String otp = service.sendOTP();

		session.setAttribute("otp", otp);
		session.setAttribute("temp_serpro", serpro);
		model.addObject("otp", "yes");

		return model;
	}

	// final request registration of Service provider
	@RequestMapping(value = "serviceprovider/registration", method = RequestMethod.POST)
	public ModelAndView finalRegisterationOfServiceProvider(@RequestParam String otp, HttpSession session) {

		ModelAndView model;

		String sotp = (String) session.getAttribute("otp");

		if (sotp.equals(otp)) {

			int i = service.registration((ServiceProvider) session.getAttribute("temp_serpro"));

			if (i == 1)
				model = new ModelAndView("serviceprovider/login");
			else
				model = new ModelAndView("redirect:/serviceprovider/register");
		} else {
			model = new ModelAndView("serviceprovider/register");
			model.addObject("otp", "yes");
			model.addObject("response", "Please Enter Correct OTP");
		}

		return model;
	}

	// login of service provider
	@RequestMapping(value = "serviceprovider/login", method = RequestMethod.POST)
	public ModelAndView serviceProviderLogin(@RequestParam String phone, @RequestParam String pass, HttpSession session) {

		ModelAndView model;

		ServiceProvider serpro = service.isValidSerPro(phone, pass);

		if (serpro == null) {
			model = new ModelAndView("serviceprovider/login");
			model.addObject("response", "Mobile no or Password is wrong");
			return model;
		}

		session.setAttribute("user", serpro);
		session.setAttribute("user_role", "serviceprovider");
		session.setAttribute("user_name", serpro.getName());

		model = new ModelAndView("redirect:/serviceprovider/home");
		return model;
	}
	
	//logout service provider 
	@RequestMapping(value="serviceprovider/logout",method=RequestMethod.GET)
	public ModelAndView logoutServiceProvider(HttpSession session){
		ModelAndView model=new ModelAndView("redirect:/serviceprovider");
		
		session.invalidate();
		return model;
	}	
}
