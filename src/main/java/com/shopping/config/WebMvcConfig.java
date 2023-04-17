package com.shopping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}")
    String uploadPath ;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // /images/로 요청이 들어오면 uploadPath 폴더에서 찾아라
        registry.addResourceHandler("/images/**").addResourceLocations(uploadPath);

    }
}
