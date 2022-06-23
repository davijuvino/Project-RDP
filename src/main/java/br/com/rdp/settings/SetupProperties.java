package br.com.rdp.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("spring.datasource")
@Data
public class SetupProperties {
	
	private String url;
	private String username;
	private String password;

}
