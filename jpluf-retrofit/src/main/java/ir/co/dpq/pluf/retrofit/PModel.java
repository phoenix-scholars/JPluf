package ir.co.dpq.pluf.retrofit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;

import com.google.gson.annotations.SerializedName;

public abstract class PModel implements IRObject {

	@Column(name = "id")
	Long id;

	@SerializedName("creation_dtime")
	Date creation;

	@SerializedName("modif_dtime")
	Date modification;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getModification() {
		return modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}

	public Map<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return map;
	}
}
