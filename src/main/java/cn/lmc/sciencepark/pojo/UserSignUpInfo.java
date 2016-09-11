package cn.lmc.sciencepark.pojo;

public class UserSignUpInfo {
	private Integer id;
	private String name;
	private String headerimg;
	private String timeday_description;
	private String timedate_description;
	private String address;
	private String phone;
	private String all_info;
	private Integer created;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeaderimg() {
		return headerimg;
	}
	public void setHeaderimg(String headerimg) {
		this.headerimg = headerimg;
	}
	public String getTimeday_description() {
		return timeday_description;
	}
	public void setTimeday_description(String timeday_description) {
		this.timeday_description = timeday_description;
	}
	public String getTimedate_description() {
		return timedate_description;
	}
	public void setTimedate_description(String timedate_description) {
		this.timedate_description = timedate_description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAll_info() {
		return all_info;
	}
	public void setAll_info(String all_info) {
		this.all_info = all_info;
	}
	public Integer getCreated() {
		return created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}
}
