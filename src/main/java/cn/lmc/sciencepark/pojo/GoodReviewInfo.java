package cn.lmc.sciencepark.pojo;

public class GoodReviewInfo {
	private Integer id;
	private Integer user_id;
	private String user_name;
	private String user_img;
	private String comment_title;
	private String comment_description;
	private Double rating_score;
	private Integer created;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public String getComment_title() {
		return comment_title;
	}
	public void setComment_title(String comment_title) {
		this.comment_title = comment_title;
	}
	public String getComment_description() {
		return comment_description;
	}
	public void setComment_description(String comment_description) {
		this.comment_description = comment_description;
	}
	public Double getRating_score() {
		return rating_score;
	}
	public void setRating_score(Double rating_score) {
		this.rating_score = rating_score;
	}
	public Integer getCreated() {
		return created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}
}
 