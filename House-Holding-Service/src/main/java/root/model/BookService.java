package root.model;

import java.sql.Date;

public class BookService {

	private int bookServiceId;
	private int customerId;
	private int serproId;
	private Date bookingDate;
	private String address;
	private int serviceId;
	private int bookServiceStatusId;
	private int areaId;
	private Date completeDate;
	
	public int getBookServiceId() {
		return bookServiceId;
	}
	public void setBookServiceId(int bookServiceId) {
		this.bookServiceId = bookServiceId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getSerproId() {
		return serproId;
	}
	public void setSerproId(int serproId) {
		this.serproId = serproId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getBookServiceStatusId() {
		return bookServiceStatusId;
	}
	public void setBookServiceStatusId(int bookServiceStatusId) {
		this.bookServiceStatusId = bookServiceStatusId;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
}
