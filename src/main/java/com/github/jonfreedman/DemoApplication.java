package com.github.jonfreedman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Bean
	Filter getAuthFilter() {
		return new Filter() {
			@Override
			public void init(FilterConfig filterConfig) throws ServletException {
			}

			@Override
			public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
				filterChain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) servletRequest) {
					@Override
					public boolean isUserInRole(String role) {
						logger.info("Checked for membership of {}", role);
						return "CN=some-group,OU=somewhere,OU=in,OU=ldap,DC=example,DC=com".equals(role);
					}
				}, servletResponse);
			}

			@Override
			public void destroy() {
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
