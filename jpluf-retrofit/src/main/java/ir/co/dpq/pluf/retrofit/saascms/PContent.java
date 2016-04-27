package ir.co.dpq.pluf.retrofit.saascms;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ir.co.dpq.pluf.retrofit.PModel;

@Entity(name = "saascms_content")
@Table(name = "saascms_content")
public class PContent extends PModel {

	@Column(name = "title")
	String title;
	
	@Column(name = "description")
	String description;
	
	@Column(name = "mime_type")
	String mime_type;
	
	@Column(name = "file_path")
	String file_path;
	
	@Column(name = "file_name")
	String file_name;
	
	@Column(name = "file_size")
	Long file_size;
	
	@Column(name = "downloads")
	Long downloads;
	
	@Column(name = "tenant")
	Long tenant;
	
	@Column(name = "submitter")
	Long submitter;

	public String getTitle() {
		return title;
	}

	public PContent setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public PContent setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getMimeType() {
		return mime_type;
	}

	public PContent setMimeType(String mimeType) {
		this.mime_type = mimeType;
		return this;
	}

	public String getFilePath() {
		return file_path;
	}

	public PContent setFilePath(String filePath) {
		this.file_path = filePath;
		return this;
	}

	public String getFileName() {
		return file_name;
	}

	public PContent setFileName(String fileName) {
		this.file_name = fileName;
		return this;
	}

	public Long getFileSize() {
		return file_size;
	}

	public PContent setFileSize(Long fileSize) {
		this.file_size = fileSize;
		return this;
	}

	public Long getDownloads() {
		return downloads;
	}

	public PContent setDownloads(Long downloads) {
		this.downloads = downloads;
		return this;
	}

	public Long getTenantId() {
		return tenant;
	}

	public PContent setTenantId(Long tenantId) {
		this.tenant = tenantId;
		return this;
	}

	public Long getSubmitterId() {
		return submitter;
	}

	public PContent setSubmitterId(Long submitterId) {
		this.submitter = submitterId;
		return this;
	}

	public Map<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if (getTitle() != null)
			map.put("title", getTitle());
		if (getDescription() != null)
			map.put("description", getDescription());
		if (getMimeType() != null)
			map.put("mime_type", getMimeType());
		if (getFileName() != null)
			map.put("file_name", getFileName());

		return map;
	}

}
