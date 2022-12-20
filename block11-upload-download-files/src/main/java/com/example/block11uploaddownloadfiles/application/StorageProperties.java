package com.example.block11uploaddownloadfiles.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "C:\\Users\\alan.sandoval\\Documents\\Java projects\\block11-upload-download-files";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
