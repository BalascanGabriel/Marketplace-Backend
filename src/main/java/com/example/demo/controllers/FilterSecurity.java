package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.User;
import com.example.demo.services.UserService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterSecurity implements Filter {

	private UserService service;
//	private List<String> securedPaths = new ArrayList<>();

	public FilterSecurity(UserService service) {
		this.service = service;
//		securedPaths.add("/rest/product/save");
//		securedPaths.add("/rest/product/delete");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String token = req.getHeader("myToken");

//		if(securedPaths.contains(req.getRequestURI())) {

		if (token == null) {
			System.out.println("NO USER TOKEN PRESENT FOR SECURED ENPOINT");
			res.sendError(401);
			return;
		}

		// path should be secured
		Optional<User> theUser = service.findByToken(token);
		if (!theUser.isPresent()) {
			System.out.println("INVALID USER TOKEN PRESENT FOR SECURED ENDPOINT");
			res.sendError(401);
			return;
		}
//		}

		System.out.println("***FILTRU TRIGGERED***");
		System.out.println("REQUEST METHOD: " + req.getMethod());
		System.out.println("REQUEST URL: " + req.getRequestURI());

		chain.doFilter(request, response);

	}

}
