package com.furniturecloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${product.images.directory}")
    private String productImagesDirectory;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println(productImagesDirectory);
        registry.addResourceHandler("/product-images/**")
                .addResourceLocations("file:" + productImagesDirectory + "/");
    }
}
