/**
 * 
 */
package com.infinera.dna.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

/**
 * @author Harshith Gowda B T
 *
 */
@Component
@Document(indexName = "dna-nbi-alarm-notify", type = "_doc")
public class NotificationResource {

	@Id
	private String id;

	private String description;

	private String type;

	private String category;

	private String version;

	private String baseType;

	private Map<String, String> characteristic;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, String> getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(Map<String, String> characteristic) {
		this.characteristic = characteristic;
	}

	public String getBaseType() {
		return baseType;
	}

	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

	@Override
	public String toString() {
		return "ModifiedResource [description=" + description + ", id=" + id + ", type=" + type + ", category="
				+ category + ", version=" + version + ", characteristic=" + characteristic + ", baseType=" + baseType
				+ "]";
	}

}
