package com.mj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

/**
 * @author bvvy
 */
@SpringBootApplication
@ImportResource(locations= {"classpath*:config.yml"})
public class ColumbuApplication {
	public static void main(String[] args) {
		SpringApplication.run(ColumbuApplication.class, args);
	}
}
