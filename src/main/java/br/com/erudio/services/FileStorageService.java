package br.com.erudio.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.erudio.config.FileStorageConfig;
import br.com.erudio.exception.FileStorageException;
import br.com.erudio.exception.MyFileNotFoundException;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch(Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be storaged", e);

        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) // filename..txt
                throw new FileStorageException("filename "+fileName+" contains invalid path sequence ");

            // mudar pra banco aws - mudar aqui
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING); // pega o input stream e joga pro targetlocation
            return fileName;
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + fileName, e);
        }
    }

    public Resource loadFileAsResource(String filename) {
        try { 
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists())
                return resource;
            throw new MyFileNotFoundException("file not found " + filename);
            

        } catch(Exception e) {
            throw new MyFileNotFoundException("file not found " + filename);

        }
    }
    
}
