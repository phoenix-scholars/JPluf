package ir.co.dpq.pluf.retrofit.saascms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ir.co.dpq.pluf.retrofit.PModel;

@Entity(name = "saascms_page")
@Table(name = "saascms_page")
public class PPage extends PModel {

	@Column(name = "name")
	String name;

	@Column(name = "content")
	Long content;

	@Column(name = "tenant")
	Long tenant;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContentId() {
		return content;
	}

	public void setContentId(Long content) {
		this.content = content;
	}

	public Long getTenantId() {
		return tenant;
	}

	public void setTenantId(Long tenant) {
		this.tenant = tenant;
	}

	public Map<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (getName() != null)
			map.put("name", getName());
		if (getContentId() != null)
			map.put("content", getContentId());
		if (getTenantId() != null)
			map.put("tenant", getTenantId());

		return map;
	}

}
