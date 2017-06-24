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
		String sql = "select customer.customer_id,customer.customer_name,customer.customer_email,area.area_name,customer.address,user_status.status_name,customer.customer_mobileno"
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
		String sql = "select service_provider.service_provider_id,service_provider.service_provider_name,service_provider.service_provider_email,area.area_name,service_provider.address,user_status.status_name,service_provider.service_provider_mobileno"
				+ " from service_provider join area on service_provider.area_id=area.area_id join user_status on service_provider.user_status_id=user_status.user_status_id";

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

	// select singlr area by id
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
}
