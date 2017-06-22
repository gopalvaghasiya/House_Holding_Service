package root.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import root.model.*;

public class ServiceDAO {
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}
	
	//validate admin and return result
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
	
	//insert city
	public int insertCity(City city){
		
		String sql="insert into city(city_id,city_name) values(DEFAULT,?)";
		
		return template.update(sql,new PreparedStatementSetter(){
			
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setString(1,city.getCityName());
			}
		});
		
	}
	
	//insert area
	public int insertArea(Area area){
		
		String sql="insert into area(area_id,city_id,area_name) values(DEFAULT,?,?)";
		
		return template.update(sql,new PreparedStatementSetter(){
			
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setInt(1, area.getCityId());
				ps.setString(2, area.getAreaName());
			}
		});
	}
	
	
	//select All City
	public ArrayList<City> selectCity(){
		
		String sql="select * from city";
		ArrayList<City> citys=template.query(sql,new ResultSetExtractor<ArrayList<City>>(){
			
			public ArrayList<City> extractData(ResultSet rs) throws SQLException{
				ArrayList<City> ct=new ArrayList<City>();
				
				while(rs.next()){
					City tmp=new City();
					
					tmp.setCityId(rs.getInt(1));
					tmp.setCityName(rs.getString(2));
					
					ct.add(tmp);
				}
				
				return ct;
			}
			
		});
		
		return citys;
	}
//	public void save(){
//		String sql="insert into student(name,pass) values('kakadiya','temp')";
//		
//		System.out.println(template.update(sql));
//	
//	}
}
