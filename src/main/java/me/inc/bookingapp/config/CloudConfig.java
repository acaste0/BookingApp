package me.inc.bookingapp.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dnzzfjdad",
                "api_key", "744871133932134",
                "api_secret", "xjrUzCFy-1MRWrdJQoZO9RfITfM"));
    }

}
