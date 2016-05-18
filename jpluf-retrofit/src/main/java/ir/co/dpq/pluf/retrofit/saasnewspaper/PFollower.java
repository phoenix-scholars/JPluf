package ir.co.dpq.pluf.retrofit.saasnewspaper;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ir.co.dpq.pluf.retrofit.PModel;

@Entity(name = "saasnewspaper_follower")
@Table(name = "saasnewspaper_follower")
public class PFollower extends PModel {

	@Column(name = "type")
	String type;

	@Column(name = "address")
	String address;

	@Column(name = "validated")
	Boolean validated;

	@Column(name = "tenant")
	Long tenant;

	public String getType() {
		return type;
	}

	public PFollower setType(String type) {
		this.type = type;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public PFollower setAddress(String address) {
		this.address = address;
		return this;
	}

	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	public Long getTenantId() {
		return tenant;
	}

	public PFollower setTenantId(Long tenant) {
		this.tenant = tenant;
		return this;
	}

	public Map<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (getAddress() != null)
			map.put("address", getAddress());
		if (getType() != null)
			map.put("type", getType());
		if(getValidated() != null)
			map.put("validated", getValidated());

		return map;
	}

}
