package com.aissatna.medilinkbackend.configuration.minio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties("spring.minio")
@Getter
@Setter
public class MinioProperties
{
	private String url;

	private String accessKey;

	private String secretKey;

	private String bucket;

	private Duration connectTimeout = Duration.ofSeconds(10);

	private Duration writeTimeout = Duration.ofSeconds(60);

	private Duration readTimeout = Duration.ofSeconds(10);
}
