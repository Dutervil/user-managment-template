package com.dev.stockApi.service;


import com.dev.stockApi.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.BiFunction;
import static com.dev.stockApi.constant.Constants.FILE_STORAGE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
@Service
public class FileStorageService {





    public String uploadPhoto(String name, MultipartFile file) {
        var photoUrl = photoFunction.apply(name, file);
       return photoUrl + "?timestamp=" + System.currentTimeMillis();
    }


    private final BiFunction<String, MultipartFile, String> photoFunction = (name, file) -> {
        var filename = name + ".png";
        try {
            var fileStorageLocation = Paths.get(FILE_STORAGE).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
            Files.copy(file.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/product/image/" + filename).toUriString();
        } catch (Exception exception) {
            throw new ApiException("unable to save image");
        }
    };
}
