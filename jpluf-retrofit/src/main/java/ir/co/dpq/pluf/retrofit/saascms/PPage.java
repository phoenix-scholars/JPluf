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

	public PPage setName(String name) {
		this.name = name;
		return this;
	}

	public Long getContentId() {
		return content;
	}

	public PPage setContentId(Long content) {
		this.content = content;
		return this;
	}

	public Long getTenantId() {
		return tenant;
	}

	public PPage setTenantId(Long tenant) {
		this.tenant = tenant;
		return this;
	}

	public Map<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (getName() != null)
			map.put("name", getName());
		if (getContentId() != null)
			map.put("content", getContentId());

		return map;
	}

}
