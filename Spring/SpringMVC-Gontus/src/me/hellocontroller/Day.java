package me.hellocontroller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Day extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest req,HttpServletResponse res,Object Handler)throws Exception{
		
		Calendar cal=Calendar.getInstance();
		int day=cal.get(cal.DAY_OF_WEEK);
		
		if(day==7){
			res.getWriter().write("the website is in under maintainnig plese visit later");
			
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//System.out.println("Handler inceptor adaptor:post Handle"+request.getRequestURI().toString());
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("after completion"+request.getRequestURI().toString());
	}
}
