package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.controllers.FilterSecurity;
import com.example.demo.services.RandomStringProvider;
import com.example.demo.services.UserService;

@Configuration
public class Configurare {

	@Autowired
	private UserService service;

	// -------------------- configurare -------------------
	@Bean
	public FilterRegistrationBean<FilterSecurity> loggingFilter() {
		FilterRegistrationBean<FilterSecurity> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new FilterSecurity(service));
		registrationBean.addUrlPatterns("/rest-secured/*");
		registrationBean.setOrder(2);

		return registrationBean;
	}

	@Bean
	public RandomStringProvider metodaCareInstantiazaAla() {
		RandomStringProvider rsp = new RandomStringProvider();
		rsp.setSirulAlaRandom("SHAORMA");
		return rsp;
	}

	// -------------------- /configurare -------------------
}
