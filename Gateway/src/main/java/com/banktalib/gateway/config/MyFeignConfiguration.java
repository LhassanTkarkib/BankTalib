package com.banktalib.gateway.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class MyFeignConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public SpringEncoder feignEncoder() {
        HttpMessageConverter<?>[] converters = {new MappingJackson2HttpMessageConverter()};
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(converters);
        return new SpringEncoder(objectFactory);
    }

    @Bean
    public SpringDecoder feignDecoder() {
        HttpMessageConverter<?>[] converters = {new MappingJackson2HttpMessageConverter()};
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(converters);
        return new SpringDecoder(objectFactory);
    }
}