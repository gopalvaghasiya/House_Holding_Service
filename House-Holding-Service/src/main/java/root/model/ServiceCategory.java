package root.model;

public class ServiceCategory {
	
	private int cateId;
	private String cateName;
	private String cateDesc;
	private String cateImg;
	
	
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getCateDesc() {
		return cateDesc;
	}
	public void setCateDesc(String cateDesc) {
		this.cateDesc = cateDesc;
	}
	public String getCateImg() {
		return cateImg;
	}
	public void setCateImg(String cateImg) {
		this.cateImg = cateImg;
	}
}
