package root.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

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
	
	//insert service category
	public int insertServiceCategory(ServiceCategory serCate){
		
		String sql="insert into service_category(service_category_id,category_name,category_description,category_image)"
				+ " values(DEFAULT,?,?,?)";
		
		return template.update(sql,new PreparedStatementSetter(){
			
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setString(1,serCate.getCateName());
				ps.setString(2,serCate.getCateDesc());
				ps.setString(3,serCate.getCateImg());
			}
		});
	}

	//insert services
	public int insertServices(Services service){
		
		String sql="insert into services(service_id,service_category_id,service_name,service_description)"
				+ "values(DEFAULT,?,?,?)";
		
		return template.update(sql,new PreparedStatementSetter(){
			
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setInt(1,service.getServiceCateId());
				ps.setString(2,service.getServiceName());
				ps.setString(3,service.getServiceDesc());
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
	
	//select all service category
	public ArrayList<ServiceCategory> selectServiceCategory(){
		
		String sql="select * from service_category";
		
		return template.query(sql,new ResultSetExtractor<ArrayList<ServiceCategory>>(){
			
			public ArrayList<ServiceCategory> extractData(ResultSet rs) throws SQLException{
				
				ArrayList<ServiceCategory> serCates=new ArrayList<>();
				
				while(rs.next()){
					ServiceCategory sc=new ServiceCategory();
					
					sc.setCateId(rs.getInt(1));
					sc.setCateName(rs.getString(2));
					sc.setCateDesc(rs.getString(3));
					sc.setCateImg(rs.getString(4));
					
					serCates.add(sc);
				}
				return serCates;
			}
		});
	}
	
	//select area JOIN city
	public ArrayList<AreaCity> selectAreaCity(){
		String sql="select area.area_id,city.city_name,area.area_name from area join city on area.city_id=city.city_id";
		
		 return template.query(sql,new ResultSetExtractor<ArrayList<AreaCity>>(){
			
			public ArrayList<AreaCity> extractData(ResultSet rs) throws SQLException{
				ArrayList<AreaCity> ac=new ArrayList<>();
				
				while(rs.next()){
					AreaCity tmp=new AreaCity();
					
					tmp.setAreaId(rs.getInt(1));
					tmp.setCityName(rs.getString(2));
					tmp.setAreaName(rs.getString(3));
					ac.add(tmp);
				}
				return ac;
			}
			
		});
	}
	
//	public void save(){
//		String sql="insert into student(name,pass) values('kakadiya','temp')";
//		
//		System.out.println(template.update(sql));
//	
//	}
}
