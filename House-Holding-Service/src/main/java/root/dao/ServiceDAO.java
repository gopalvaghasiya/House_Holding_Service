package root.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import root.model.Admin;

public class ServiceDAO {
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}
	
	public Admin isValidAdmin(String userName,String password){
		
		String sql="select * from admin where user_name=? and password=?";
		
		return template.query(sql, new PreparedStatementSetter(){
			
			public void  setValues(PreparedStatement ps) throws SQLException{
				ps.setString(1, userName);
				ps.setString(2,password);
			}
			},
				new ResultSetExtractor<Admin>()
				{
					public Admin extractData(ResultSet rs) throws SQLException{
						
						if(rs.next()){
							Admin ad=new Admin();
							
							ad.setAdminId(rs.getInt(1));
							ad.setUserName(rs.getString(2));
							
							return ad;
						}
						return null;
					}
				}
				);
		
		
	}
//	public void save(){
//		String sql="insert into student(name,pass) values('kakadiya','temp')";
//		
//		System.out.println(template.update(sql));
//	
//	}
}
