package me.inc.bookingapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface CloudService {

    String upload(MultipartFile multipartFile) throws IOException;

    String upload(File file) throws IOException;
}
