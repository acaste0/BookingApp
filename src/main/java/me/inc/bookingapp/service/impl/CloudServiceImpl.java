package me.inc.bookingapp.service.impl;

import com.cloudinary.Cloudinary;
import me.inc.bookingapp.service.CloudService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
@Service
public class CloudServiceImpl implements CloudService {

    private final Cloudinary cloudinary;

    public CloudServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp-file", multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return this.cloudinary.uploader().upload(file, Collections.emptyMap()).get("url").toString();
    }


}
