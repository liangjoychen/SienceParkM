package cn.lmc.sciencepark.pojo;

public class ProjectCommentListInfo {
	private Integer id;
	private Integer user_id;
	private String user_name;
	private String user_img;
	private String comment_title;
	private String comment_images;
	private String comment_description;
	private Integer share_number;
	private Integer praise_number;
	private Integer reply_number;
	private Double rating_score;
	private Integer type;
	private Integer is_praise;
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
	public String getComment_images() {
		return comment_images;
	}
	public void setComment_images(String comment_images) {
		this.comment_images = comment_images;
	}
	public String getComment_description() {
		return comment_description;
	}
	public void setComment_description(String comment_description) {
		this.comment_description = comment_description;
	}
	public Integer getShare_number() {
		return share_number;
	}
	public void setShare_number(Integer share_number) {
		this.share_number = share_number;
	}
	public Integer getPraise_number() {
		return praise_number;
	}
	public void setPraise_number(Integer praise_number) {
		this.praise_number = praise_number;
	}
	public Integer getReply_number() {
		return reply_number;
	}
	public void setReply_number(Integer reply_number) {
		this.reply_number = reply_number;
	}
	public Double getRating_score() {
		return rating_score;
	}
	public void setRating_score(Double rating_score) {
		this.rating_score = rating_score;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIs_praise() {
		return is_praise;
	}
	public void setIs_praise(Integer is_praise) {
		this.is_praise = is_praise;
	}
	public Integer getCreated() {
		return created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}
}
