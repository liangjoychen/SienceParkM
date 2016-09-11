package cn.lmc.sciencepark.pojo;

public class ProjectDetailInfo {
	private Integer id;
	private String name;
	private String address;
	private String phone;
	private String details_description;
	private String timeday_description;
	private String timedate_description;
	private Float rating_score;
	private Float lat;
	private Float lng;
	private Integer is_shoucang;
	private Integer is_praise;
	private Integer collection_number;
	
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
	public String getDetails_description() {
		return details_description;
	}
	public void setDetails_description(String details_description) {
		this.details_description = details_description;
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
	public Float getRating_score() {
		return rating_score;
	}
	public void setRating_score(Float rating_score) {
		this.rating_score = rating_score;
	}
	public Float getLat() {
		return lat;
	}
	public void setLat(Float lat) {
		this.lat = lat;
	}
	public Float getLng() {
		return lng;
	}
	public void setLng(Float lng) {
		this.lng = lng;
	}
	public Integer getIs_shoucang() {
		return is_shoucang;
	}
	public void setIs_shoucang(Integer is_shoucang) {
		this.is_shoucang = is_shoucang;
	}
	public Integer getIs_praise() {
		return is_praise;
	}
	public void setIs_praise(Integer is_praise) {
		this.is_praise = is_praise;
	}
	public Integer getCollection_number() {
		return collection_number;
	}
	public void setCollection_number(Integer collection_number) {
		this.collection_number = collection_number;
	}
}
