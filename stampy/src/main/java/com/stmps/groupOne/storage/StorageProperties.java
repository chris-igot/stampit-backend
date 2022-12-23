package com.stmps.groupOne.storage;

import java.beans.JavaBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
@JavaBean
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */

	@Value("${UPLOADS_PATH}")
	private String location = "/home/ci/stampit_uploads";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
