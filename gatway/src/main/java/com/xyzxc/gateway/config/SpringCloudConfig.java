package com.xyzxc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xyzxc.gateway.filter.CustomFilter;
import com.xyzxc.gateway.filter.CustomFilter.Config;

@Configuration
public class SpringCloudConfig {

	@Bean
	RouteLocator buildRouteLocater(RouteLocatorBuilder builder, CustomFilter customFilter) {
		return builder.routes().route(r -> r.path("/currexg/**").filters(f->f.setResponseHeader("custm", "custom header")).uri("http://localhost:8004"))
				.route(r -> r.path("/price/**").filters(f -> f.filter(customFilter.apply(new Config()))
				).uri("http://localhost:8002")).route(r -> r.path("/inventory/**").uri("http://localhost:8003"))
				.route(r -> r.path("/product/**").uri("http://localhost:8004")).build();

	}

}
