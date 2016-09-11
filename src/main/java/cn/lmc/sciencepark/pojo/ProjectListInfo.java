package cn.lmc.sciencepark.pojo;

public class ProjectListInfo {
	private Integer id;
	private String name;
	private String headerimg;
	private Float rating_score;
	private Integer is_great;
	private Integer type;
	private String phone;
	private Integer city_id;
	private Float lat;
	private Float lng;
	private Float recently;
	
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
	public Float getRating_score() {
		return rating_score;
	}
	public void setRating_score(Float rating_score) {
		this.rating_score = rating_score;
	}
	public Integer getIs_great() {
		return is_great;
	}
	public void setIs_great(Integer is_great) {
		this.is_great = is_great;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
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
	public Float getRecently() {
		return recently;
	}
	public void setRecently(Float recently) {
		this.recently = recently;
	}
}
