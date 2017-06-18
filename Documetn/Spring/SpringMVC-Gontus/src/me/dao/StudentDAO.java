package me.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class StudentDAO {

	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}
	
	public void save(){
		String sql="insert into student(name,pass) values('hardik','temp')";
		
		System.out.println(template.update(sql));
	}
}
