package com.aptech.spring01.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("assets")
public class StorageConfigProperties {
    /**
     * Folder location for storing files
     */
    private String location = "assets";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
