package com.myblogapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MyBlogAppApplication {

	public static void main(String[] args)

	{
		SpringApplication.run(MyBlogAppApplication.class, args);
	}
	@Bean
    public ModelMapper getModelMapper()
  {
	  return new ModelMapper();
  }

}
