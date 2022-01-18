package br.com.erudio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// vai ler a propriedade file.upload-dir e setar aqui
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    
}
