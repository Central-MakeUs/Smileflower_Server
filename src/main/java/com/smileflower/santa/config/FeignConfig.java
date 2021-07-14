package com.smileflower.santa.config;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

public class FeignConfig{

//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
//            @Value("${scrooge-mcduck.authentication.username}") String username,
//            @Value("${scrooge-mcduck.authentication.password}") String password) {
//        return new BasicAuthRequestInterceptor(username, password);
//    }

    @Bean
    Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new SpringFormEncoder(new SpringEncoder(converters));
    }
}
