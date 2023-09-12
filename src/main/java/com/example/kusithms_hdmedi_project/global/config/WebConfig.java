package com.example.kusithms_hdmedi_project.global.config;

import com.example.kusithms_hdmedi_project.global.converter.SearchTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SearchTypeConverter());
    }
}
