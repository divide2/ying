package com.mj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author bvvy
 */
@SpringBootApplication
public class ColumbuApplication {
	public static void main(String[] args) {
		SpringApplication.run(ColumbuApplication.class, args);
	}
}