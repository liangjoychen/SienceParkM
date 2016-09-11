package cn.lmc.sciencepark.pojo;

public class ProjectImageInfo {
	private Integer id;
	private Integer pid;
	private String image_url;
	private Integer type;
	private Integer created;
	private Integer updated;
	private Integer shouye_type;
	private String p_name;
	private Integer is_renqi;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
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
	public Integer getShouye_type() {
		return shouye_type;
	}
	public void setShouye_type(Integer shouye_type) {
		this.shouye_type = shouye_type;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Integer getIs_renqi() {
		return is_renqi;
	}
	public void setIs_renqi(Integer is_renqi) {
		this.is_renqi = is_renqi;
	}
}
