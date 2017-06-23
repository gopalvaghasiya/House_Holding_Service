package root.model;

public class Services {

	private int serviceId;
	private int serviceCateId;
	private String serviceName;
	private String serviceDesc;
	
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getServiceCateId() {
		return serviceCateId;
	}
	public void setServiceCateId(int serviceCateId) {
		this.serviceCateId = serviceCateId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
}
