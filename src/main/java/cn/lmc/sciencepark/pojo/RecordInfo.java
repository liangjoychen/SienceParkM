package cn.lmc.sciencepark.pojo;

public class RecordInfo {
	private Integer id;
	private Integer user_id;
	private Integer project_id;
	private Integer type;
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
	public Integer getProject_id() {
		return project_id;
	}
	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
