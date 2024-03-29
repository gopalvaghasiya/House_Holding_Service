package root.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import root.model.*;

public class ServiceDAO {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {

		this.template = template;
	}

	// validate admin and return result
	public Admin isValidAdmin(String userName, String password) {

		String sql = "select * from admin where user_name=? and password=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userName);
				ps.setString(2, password);
			}
		}, new ResultSetExtractor<Admin>() {
			public Admin extractData(ResultSet rs) throws SQLException {

				if (rs.next()) {
					Admin ad = new Admin();

					ad.setAdminId(rs.getInt(1));
					ad.setUserName(rs.getString(2));

					return ad;
				}
				return null;
			}
		});
	}

	// check is customer number is registered or not
	public int isCustomerRegistered(String phone) {
		String sql = "select count(*) from customer where customer_mobileno=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, phone);
			}
		}, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException {

				if (rs.next()) {
					int i = rs.getInt(1);

					if (i > 0)
						return 1;
					else
						return 0;
				}
				return 0;
			}
		});
	}

	// check is service provider number is registered or not
	public int isServiceProviderRegistered(String phone) {
		String sql = "select count(*) from service_provider where service_provider_mobileno=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, phone);
			}
		}, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException {

				if (rs.next()) {
					int i = rs.getInt(1);

					if (i > 0)
						return 1;
					else
						return 0;
				}
				return 0;
			}
		});
	}

	// validate customer and return result
	public Customer isValidCustomer(String phone, String pass) {

		String sql = "select customer_id,customer_name,customer_email,area_id,user_status_id,customer_mobileno from customer where customer_mobileno=? and password=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, phone);
				ps.setString(2, pass);
			}
		}, new ResultSetExtractor<Customer>() {
			public Customer extractData(ResultSet rs) throws SQLException {

				if (rs.next()) {
					Customer cust = new Customer();
					cust.setCustomerId(rs.getInt(1));
					cust.setCustomerName(rs.getString(2));
					cust.setEmail(rs.getString(3));
					cust.setArea("" + rs.getInt(4));
					cust.setStatus(""+rs.getInt(5));
					cust.setMobileNo(rs.getString(6));

					return cust;
				}
				return null;
			}
		});
	}

	// validate service provider and return result
	public ServiceProvider isValidServiceprovider(String phone, String pass) {

		String sql = "select service_provider_id,service_provider_name,service_provider_email,user_status_id,service_provider_mobileno from service_provider where service_provider_mobileno=? and password=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, phone);
				ps.setString(2, pass);
			}
		}, new ResultSetExtractor<ServiceProvider>() {
			public ServiceProvider extractData(ResultSet rs) throws SQLException {

				if (rs.next()) {
					ServiceProvider serpro = new ServiceProvider();
					serpro.setServiceProviderId(rs.getInt(1));
					serpro.setName(rs.getString(2));
					serpro.setEmail(rs.getString(3));
					serpro.setStatus(""+rs.getInt(4));
					serpro.setMobileNo(rs.getString(5));

					return serpro;
				}
				return null;
			}
		});
	}

	// **************************Insert
	// data****************************************//
	// insert city
	public int insertCity(City city) {

		String sql = "insert into city(city_id,city_name) values(DEFAULT,?)";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, city.getCityName());
			}
		});
	}

	// insert area
	public int insertArea(Area area) {

		String sql = "insert into area(area_id,city_id,area_name) values(DEFAULT,?,?)";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, area.getCityId());
				ps.setString(2, area.getAreaName());
			}
		});
	}

	// insert service category
	public int insertServiceCategory(ServiceCategory serCate) {

		String sql = "insert into service_category(service_category_id,category_name,category_description,category_image)"
				+ " values(DEFAULT,?,?,?)";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, serCate.getCateName());
				ps.setString(2, serCate.getCateDesc());
				ps.setString(3, serCate.getCateImg());
			}
		});
	}

	// insert services
	public int insertServices(Services service) {

		String sql = "insert into services(service_id,service_category_id,service_name,service_description)"
				+ "values(DEFAULT,?,?,?)";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, service.getServiceCateId());
				ps.setString(2, service.getServiceName());
				ps.setString(3, service.getServiceDesc());
			}
		});
	}

	// insert customer registration details
	public int insertCustomerDetail(Customer customer) {
		String sql = "insert into customer(customer_id,customer_name,customer_email,password,area_id,address,user_status_id,customer_mobileno)"
				+ "values (DEFAULT,?,?,?,?,?,?,?)";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, customer.getCustomerName());
				ps.setString(2, customer.getEmail());
				ps.setString(3, customer.getPassword());
				ps.setInt(4, Integer.parseInt(customer.getArea()));
				ps.setString(5, customer.getAddress());
				ps.setInt(6, Integer.parseInt(customer.getStatus()));
				ps.setLong(7, Long.parseLong(customer.getMobileNo()));
			}
		});
	}

	// insert service provider registration details
	public int insertServiceProviderDetail(ServiceProvider serpro) {
		String sql = "insert into service_provider(service_provider_id,service_provider_name,service_provider_email,password,area_id,address,user_status_id,service_provider_mobileno)"
				+ "values (DEFAULT,?,?,?,?,?,?,?)";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, serpro.getName());
				ps.setString(2, serpro.getEmail());
				ps.setString(3, serpro.getPassword());
				ps.setInt(4, Integer.parseInt(serpro.getArea()));
				ps.setString(5, serpro.getAddress());
				ps.setInt(6, Integer.parseInt(serpro.getStatus()));
				ps.setLong(7, Long.parseLong(serpro.getMobileNo()));
			}
		});
	}

	// insert service provider skill it return 0 if service already added other
	// wise return 1
	public int insertServiceProviderSkill(int serpro_id, int service_id) {

		String sql = "select * from skill where service_provider_id=? and service_id=?";

		int i = template.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, serpro_id);
				ps.setInt(2, service_id);
			}
		}, new ResultSetExtractor<Integer>() {

			public Integer extractData(ResultSet rs) throws SQLException {

				if (rs.next()) {
					int i = rs.getInt(1);

					if (i > 0) {
						return 1;
					} else
						return 0;
				}
				return 0;
			}
		});

		if (i == 1) {
			return 0;
		}

		sql = "insert into skill(skill_id,service_provider_id,service_id) values(DEFAULT,?,?)";

		return template.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, serpro_id);
				ps.setInt(2, service_id);
			}
		});
	}

	// booked service
	public int bookService(BookService bs) {

		String sql = "insert into booked_service(booked_service_id,customer_id,service_provider_id,booking_date,address,service_id,booked_service_status_id,area_id)"
				+ " values(DEFAULT,?,?,?,?,?,?,?)";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, bs.getCustomerId());
				ps.setInt(2, bs.getSerproId());
				ps.setDate(3, bs.getBookingDate());
				ps.setString(4, bs.getAddress());
				ps.setInt(5, bs.getServiceId());
				ps.setInt(6, bs.getBookServiceStatusId());
				ps.setInt(7, bs.getAreaId());
			}
		});
	}

	// *****************************Select Data****************************

	// select customer by id
	public Customer selectCustomerById(int cust_id) {

		String sql = "select customer.area_id,customer.address from customer where customer_id=?";

		return template.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, cust_id);
			}
		}, new ResultSetExtractor<Customer>() {
			public Customer extractData(ResultSet rs) throws SQLException {
				if (rs.next()) {
					Customer cust = new Customer();
					cust.setArea(rs.getString(1));
					cust.setAddress(rs.getString(2));
					return cust;
				}
				return null;
			}
		});
	}

	// select All City
	public ArrayList<City> selectCity() {

		String sql = "select * from city";
		ArrayList<City> citys = template.query(sql, new ResultSetExtractor<ArrayList<City>>() {

			public ArrayList<City> extractData(ResultSet rs) throws SQLException {
				ArrayList<City> ct = new ArrayList<City>();

				while (rs.next()) {
					City tmp = new City();

					tmp.setCityId(rs.getInt(1));
					tmp.setCityName(rs.getString(2));

					ct.add(tmp);
				}

				return ct;
			}

		});

		return citys;
	}

	// select all service category
	public ArrayList<ServiceCategory> selectServiceCategory() {

		String sql = "select * from service_category";

		return template.query(sql, new ResultSetExtractor<ArrayList<ServiceCategory>>() {

			public ArrayList<ServiceCategory> extractData(ResultSet rs) throws SQLException {

				ArrayList<ServiceCategory> serCates = new ArrayList<>();

				while (rs.next()) {
					ServiceCategory sc = new ServiceCategory();

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

	// select area JOIN city
	public ArrayList<AreaCity> selectAreaCity() {
		String sql = "select area.area_id,city.city_name,area.area_name from area join city on area.city_id=city.city_id";

		return template.query(sql, new ResultSetExtractor<ArrayList<AreaCity>>() {

			public ArrayList<AreaCity> extractData(ResultSet rs) throws SQLException {
				ArrayList<AreaCity> ac = new ArrayList<>();

				while (rs.next()) {
					AreaCity tmp = new AreaCity();
					Area area = new Area();
					City city = new City();

					area.setAreaId(rs.getInt(1));
					city.setCityName(rs.getString(2));
					area.setAreaName(rs.getString(3));

					tmp.setArea(area);
					tmp.setCity(city);

					ac.add(tmp);
				}
				return ac;
			}

		});
	}

	// select Services JOIN ServiceCategory
	public ArrayList<ServicesJCategory> selectServicesJCategory() {
		String sql = "select services.service_id,service_category.category_name,services.service_name,services.service_description"
				+ " from services join service_category on services.service_category_id=service_category.service_category_id";

		return template.query(sql, new ResultSetExtractor<ArrayList<ServicesJCategory>>() {

			public ArrayList<ServicesJCategory> extractData(ResultSet rs) throws SQLException {
				ArrayList<ServicesJCategory> al = new ArrayList<>();

				while (rs.next()) {
					ServicesJCategory tmp = new ServicesJCategory();
					Services ser = new Services();
					ServiceCategory sc = new ServiceCategory();

					ser.setServiceId(rs.getInt(1));
					sc.setCateName(rs.getString(2));
					ser.setServiceName(rs.getString(3));
					ser.setServiceDesc(rs.getString(4));

					tmp.setServices(ser);
					tmp.setServiceCategory(sc);

					al.add(tmp);
				}
				return al;
			}

		});
	}

	// select customer - join
	public ArrayList<Customer> selectAllCustomer() {
		String sql = "select customer.customer_id,customer.customer_name,customer.customer_email,area.area_name,customer.address,user_status.user_status_id,customer.customer_mobileno"
				+ " from customer join area on customer.area_id=area.area_id join user_status on customer.user_status_id=user_status.user_status_id";

		return template.query(sql, new ResultSetExtractor<ArrayList<Customer>>() {

			public ArrayList<Customer> extractData(ResultSet rs) throws SQLException {

				ArrayList<Customer> al = new ArrayList<>();
				while (rs.next()) {
					Customer cust = new Customer();

					cust.setCustomerId(rs.getInt(1));
					cust.setCustomerName(rs.getString(2));
					cust.setEmail(rs.getString(3));
					cust.setArea(rs.getString(4));
					cust.setAddress(rs.getString(5));
					cust.setStatus(rs.getString(6));
					cust.setMobileNo(rs.getString(7));

					al.add(cust);
				}
				return al;
			}
		});
	}

	// select Service Provider - join
	public ArrayList<ServiceProvider> selectAllServiceProvider() {
		String sql = "select service_provider.service_provider_id,service_provider.service_provider_name,service_provider.service_provider_email,area.area_name,service_provider.address,user_status.user_status_id,service_provider.service_provider_mobileno"
				+ " from service_provider left join area on service_provider.area_id=area.area_id join user_status on service_provider.user_status_id=user_status.user_status_id";

		return template.query(sql, new ResultSetExtractor<ArrayList<ServiceProvider>>() {

			public ArrayList<ServiceProvider> extractData(ResultSet rs) throws SQLException {

				ArrayList<ServiceProvider> al = new ArrayList<>();
				while (rs.next()) {
					ServiceProvider sp = new ServiceProvider();

					sp.setServiceProviderId(rs.getInt(1));
					sp.setName(rs.getString(2));
					sp.setEmail(rs.getString(3));
					sp.setArea(rs.getString(4));
					sp.setAddress(rs.getString(5));
					sp.setStatus(rs.getString(6));
					sp.setMobileNo(rs.getString(7));

					al.add(sp);
				}
				return al;
			}
		});
	}

	// ***************************Select by
	// id**************************************

	// select single area by id
	public Area selectArea(int id) {
		String sql = "select * from area where area_id=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new ResultSetExtractor<Area>() {
			public Area extractData(ResultSet rs) throws SQLException {
				Area a = new Area();

				if (rs.next()) {
					a.setAreaId(rs.getInt(1));
					a.setCityId(rs.getInt(2));
					a.setAreaName(rs.getString(3));

					return a;
				}
				return null;
			}
		}

		);
	}

	// select single City by id
	public City selectCity(int id) {
		String sql = "select * from city where city_id=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new ResultSetExtractor<City>() {
			public City extractData(ResultSet rs) throws SQLException {
				City c = new City();

				if (rs.next()) {
					c.setCityId(rs.getInt(1));
					c.setCityName(rs.getString(2));

					return c;
				}
				return null;
			}
		});
	}

	// select service by id
	public Services selectService(int id) {
		String sql = "select * from services where service_id=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new ResultSetExtractor<Services>() {
			public Services extractData(ResultSet rs) throws SQLException {
				Services s = new Services();

				if (rs.next()) {
					s.setServiceId(rs.getInt(1));
					s.setServiceCateId(rs.getInt(2));
					s.setServiceName(rs.getString(3));
					s.setServiceDesc(rs.getString(4));

					return s;
				}
				return null;
			}
		});
	}

	// select service by category id
	public ArrayList<Services> selectServiceByCategoryId(int cateid) {
		String sql = "select * from services where service_category_id=?";

		return template.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, cateid);
			}
		}, new ResultSetExtractor<ArrayList<Services>>() {
			public ArrayList<Services> extractData(ResultSet rs) throws SQLException {

				ArrayList<Services> ser = new ArrayList<>();

				while (rs.next()) {
					Services s = new Services();
					s.setServiceId(rs.getInt(1));
					s.setServiceCateId(rs.getInt(2));
					s.setServiceName(rs.getString(3));
					s.setServiceDesc(rs.getString(4));

					ser.add(s);
				}
				return ser;
			}
		});
	}

	// select service category by id
	public ServiceCategory selectServiceCategory(int id) {
		String sql = "select * from service_category where service_category_id=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new ResultSetExtractor<ServiceCategory>() {
			public ServiceCategory extractData(ResultSet rs) throws SQLException {
				ServiceCategory s = new ServiceCategory();

				if (rs.next()) {
					s.setCateId(rs.getInt(1));
					s.setCateName(rs.getString(2));
					s.setCateDesc(rs.getString(3));
					s.setCateImg(rs.getString(4));

					return s;
				}
				return null;
			}
		});
	}

	// select area by city id
	public ArrayList<Area> selectAreaByCityId(int id) {

		String sql = "select * from area where city_id=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}
		}, new ResultSetExtractor<ArrayList<Area>>() {

			public ArrayList<Area> extractData(ResultSet rs) throws SQLException {

				ArrayList<Area> al = new ArrayList<>();

				while (rs.next()) {
					Area a = new Area();

					a.setAreaId(rs.getInt(1));
					a.setCityId(rs.getInt(2));
					a.setAreaName(rs.getString(3));

					al.add(a);
				}
				return al;
			}
		});
	}

	// select skill join service join service category by service provider id
	public ArrayList<ServicesJCategory> selectSkill(int spi) {
		String sql = "select skill.skill_id,service_category.category_name,services.service_name,services.service_description"
				+ " from skill join services on skill.service_id=services.service_id "
				+ "join public.service_category on services.service_category_id=service_category.service_category_id "
				+ "where skill.service_provider_id=?";

		return template.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, spi);
			}
		}, new ResultSetExtractor<ArrayList<ServicesJCategory>>() {

			public ArrayList<ServicesJCategory> extractData(ResultSet rs) throws SQLException {
				ArrayList<ServicesJCategory> al = new ArrayList<>();

				while (rs.next()) {
					ServicesJCategory tmp = new ServicesJCategory();
					Services ser = new Services();
					ServiceCategory sc = new ServiceCategory();

					ser.setServiceId(rs.getInt(1));
					sc.setCateName(rs.getString(2));
					ser.setServiceName(rs.getString(3));
					ser.setServiceDesc(rs.getString(4));

					tmp.setServices(ser);
					tmp.setServiceCategory(sc);

					al.add(tmp);
				}
				return al;
			}

		});
	}

	// select service provider by service id
	public ArrayList<ServiceProvider> selectServiceProviderByServiceId(int service_id, int area) {
		String sql = "select service_provider.service_provider_id,service_provider.service_provider_name,service_provider.service_provider_mobileno,service_provider.address from "
				+ "public.skill join public.service_provider on skill.service_provider_id=service_provider.service_provider_id where skill.service_id=? and service_provider.area_id=?";

		return template.query(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, service_id);
				ps.setInt(2, area);
			}
		}, new ResultSetExtractor<ArrayList<ServiceProvider>>() {
			public ArrayList<ServiceProvider> extractData(ResultSet rs) throws SQLException {
				ArrayList<ServiceProvider> spal = new ArrayList<>();

				while (rs.next()) {
					ServiceProvider sp = new ServiceProvider();
					sp.setServiceProviderId(rs.getInt(1));
					sp.setName(rs.getString(2));
					sp.setMobileNo(rs.getString(3));
					sp.setAddress(rs.getString(4));

					spal.add(sp);
				}
				return spal;
			}
		});
	}

	// ******************************UPDATE*******************************

	// update area
	public int updateArea(Area area) {

		String sql = "update area set city_id=?,area_name=? where area_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, area.getCityId());
				ps.setString(2, area.getAreaName());
				ps.setInt(3, area.getAreaId());
			}
		});
	}

	// Update city
	public int updateCity(City city) {

		String sql = "update city set city_name=? where city_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, city.getCityName());
				ps.setInt(2, city.getCityId());
			}
		});
	}

	// Update services
	public int updateServices(Services service) {

		String sql = "update services set service_category_id=?,service_name=?,service_description=? where service_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, service.getServiceCateId());
				ps.setString(2, service.getServiceName());
				ps.setString(3, service.getServiceDesc());
				ps.setInt(4, service.getServiceId());
			}
		});
	}

	// Update service category
	public int updateServiceCategory(ServiceCategory sercate) {

		String sql = "update service_category set category_name=?,category_description=?,category_image=? where service_category_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, sercate.getCateName());
				ps.setString(2, sercate.getCateDesc());
				ps.setString(3, sercate.getCateImg());
				ps.setInt(4, sercate.getCateId());
			}
		});
	}

	// Update user status
	public int updateUserStatus(int user_type, Customer cust) {
		String sql = "";

		if (user_type == 1) {
			sql = "update customer set user_status_id=? where customer_id=?";
		} else if (user_type == 2) {
			sql = "update service_provider set user_status_id=? where service_provider_id=?";
		}

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(cust.getStatus()));
				ps.setInt(2, cust.getCustomerId());
			}
		});
	}

	// ***********************DELETE***********************************//

	// delete area
	public int deleteArea(int area_id) {

		String sql = "delete from area where area_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, area_id);
			}
		});
	}

	// delete city
	public int deleteCity(int city_id) {
		String sql = "delete from city where city_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, city_id);
			}
		});
	}

	// delete services
	public int deleteService(int service_id) {
		String sql = "delete from services where service_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, service_id);
			}
		});
	}

	// delete service category
	public int deleteServiceCategory(int service_category_id) {
		String sql = "delete from service_category where service_category_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, service_category_id);
			}
		});
	}

	// delete service provider skill
	public int deleteServiceProviderSkill(int skill_id) {
		String sql = "delete from skill where skill_id=?";

		return template.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, skill_id);
			}
		});
	}
}
