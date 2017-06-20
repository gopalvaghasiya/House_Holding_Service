package root.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class ServiceDAO {
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}
	
	public void save(){
		String sql="insert into student(name,pass) values('kakadiya','temp')";
		
		System.out.println(template.update(sql));
	
	}
}
