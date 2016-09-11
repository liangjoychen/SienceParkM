package cn.lmc.sciencepark.pojo;

public class UserFeedBackInfo {
	private Integer id;
	private Integer user_id;
	private String content;
	private Integer is_read;
	private String email;
	private Integer created;
	private Integer updated;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIs_read() {
		return is_read;
	}
	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCreated() {
		return created;
	}
	public void setCreated(Integer created) {
		this.created = created;
	}
	public Integer getUpdated() {
		return updated;
	}
	public void setUpdated(Integer updated) {
		this.updated = updated;
	}
}
