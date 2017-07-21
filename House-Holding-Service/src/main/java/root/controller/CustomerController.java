package root.controller;

import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
public class CustomerController {

	@Autowired
	ServicesHelper service;

	//check customer is logedin or not
	public boolean isLogedin(HttpSession session){
	
		String user_role=(String)session.getAttribute("user_role");
		if(user_role!=null && user_role.equals("customer")){
			return true;
		}
		else{
			return false;
		}
	}
	
	// request for customer home page
	@RequestMapping(value = "customer", method = RequestMethod.GET)
	public ModelAndView goToHomePage() {

		ModelAndView model=new ModelAndView("customer/home");
		model.addObject("servicecate",service.getServiceCategory());
		return model;
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

	// request registration of customer
	@RequestMapping(value = "customer/register", method = RequestMethod.POST)
	public ModelAndView RegisterationOfCustomer( @ModelAttribute("customer")@Valid Customer customer,BindingResult result, HttpSession session) throws URISyntaxException {

		ModelAndView model = new ModelAndView("customer/register");
		
		if(result.hasErrors()){
			model=new ModelAndView("customer/register");
			return model;
		}
		if(service.isCustomerRegistered(customer.getMobileNo())==1){
			model = new ModelAndView("redirect:/customer/register");
			model.addObject("otp","no");
			model.addObject("response","mobile no is registered");
			return model;
		}
		String otp=service.sendOTP();
		
		session.setAttribute("otp",otp);
		session.setAttribute("temp_cust", customer);
		model.addObject("otp", "yes");

		return model;
	}

	// final request registration of customer
	@RequestMapping(value = "customer/registration", method = RequestMethod.POST)
	public ModelAndView finalRegisterationOfCustomer(@RequestParam String otp, HttpSession session) {

		ModelAndView model;

		String sotp=(String)session.getAttribute("otp");
		
		if(sotp.equals(otp)){
			
			int i = service.registration((Customer)session.getAttribute("temp_cust"));
			
			if(i==1)
				model = new ModelAndView("redirect:/customer/login");
			else
				model=new ModelAndView("redirect:/register");
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
	
	// login of customer
	@RequestMapping(value="customer/login",method=RequestMethod.POST)
	public ModelAndView customerLogin(@RequestParam String phone,@RequestParam String pass,HttpSession session){
		
		ModelAndView model;
		
		Customer cust=service.isValidCust(phone, pass);
		
		if(cust==null){
			model=new ModelAndView("customer/login");
			model.addObject("response","Mobile no or Password is wrong");
			return model;
		}
		
		session.setAttribute("user",cust.getCustomerId());
		session.setAttribute("user_role","customer");
		session.setAttribute("user_name",cust.getCustomerName());
		
		model=new ModelAndView("redirect:/customer");
		return model;
	}
	
	//logout customer 
	@RequestMapping(value="customer/logout",method=RequestMethod.GET)
	public ModelAndView logoutCutomer(HttpSession session){
		ModelAndView model=new ModelAndView("redirect:/customer");
		
		session.invalidate();
		return model;
	}
	
	// book service
	@RequestMapping(value="customer/book_service",method=RequestMethod.GET)
	public ModelAndView bookService(@RequestParam int cateid,HttpSession session){
		
		ModelAndView model;
		if(!isLogedin(session)){
			model=new ModelAndView("customer/login");
			model.addObject("msg","Please Login");
			return model;
		}
		model=new ModelAndView("customer/book_service");

		model.addObject("services",service.getServicesByCategoryId(cateid));
		
		return model;
	}
	
	// show service provider
	@RequestMapping(value="customer/show_serviceprovider",method=RequestMethod.POST)
	public ModelAndView showServiceProvider(@RequestParam int service_id,HttpSession session){
		
		ModelAndView model;
		if(!isLogedin(session)){
			model=new ModelAndView("customer/login");
			model.addObject("msg","Please Login");
			return model;
		}
		
		session.setAttribute("service_id",service_id);
		model=new ModelAndView("customer/show_service_provider");

		model.addObject("serpro",service.selectServiceProviderByServiceId(service_id));
		
		return model;
	}
	
	// select service provider and book service finally
	@RequestMapping(value="customer/book_service_final",method=RequestMethod.GET)
	public ModelAndView bookeServiceFinal(@RequestParam int serpro_id,HttpSession session){
		
		ModelAndView model;
		if(!isLogedin(session)){
			model=new ModelAndView("customer/login");
			model.addObject("msg","Please Login");
			return model;
		}
		
		int cust_id=(int)session.getAttribute("user");
		
		Customer cust=service.selectCustomer(cust_id);
		int service_id=(int)session.getAttribute("service_id");
		session.removeAttribute("service_id");
		
		BookService book=new BookService();
		
		book.setCustomerId(cust_id);
		book.setSerproId(serpro_id);
		book.setBookingDate(new Date(System.currentTimeMillis()));
		book.setAddress(cust.getAddress());
		book.setServiceId(service_id);
		book.setBookServiceStatusId(1);
		book.setAreaId(Integer.parseInt(cust.getArea()));
		
		int status=service.bookeService(book);
		model=new ModelAndView("customer/home");
		if(status==1){
			model.addObject("response","Booked Success");
		}
		else{
			model.addObject("response","Booking Failed");
		}
		model.addObject("servicecate",service.getServiceCategory());
		return model;
	}
}
