package com.selfpaidgrocerysystemservices;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
public class SelfpaidGrocerysystemServicesApplication {

	@Autowired
    DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(SelfpaidGrocerysystemServicesApplication.class, args);
	}
	
	/*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting-javaconfig").allowedOrigins("*");
            }
        };
    }*/
}
