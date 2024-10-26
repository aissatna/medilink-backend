package com.aissatna.medilinkbackend.configuration.minio;

import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
@ConditionalOnClass(MinioClient.class)
@EnableConfigurationProperties(MinioProperties.class)
@AllArgsConstructor
public class MinioConfiguration
{

	private MinioProperties minioProperties;

	@Bean
	public MinioClient minioClient() {

		MinioClient minioClient;
		if (!configuredProxy()) {
			minioClient = MinioClient.builder()
					.endpoint(minioProperties.getUrl())
					.credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
					.build();
		} else {
			minioClient = MinioClient.builder()
					.endpoint(minioProperties.getUrl())
					.credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
					.httpClient(client())
					.build();
		}
		minioClient.setTimeout(
				minioProperties.getConnectTimeout().toMillis(),
				minioProperties.getWriteTimeout().toMillis(),
				minioProperties.getReadTimeout().toMillis()
		);

		return minioClient;
	}

	private boolean configuredProxy() {
		String httpHost = System.getProperty("http.proxyHost");
		String httpPort = System.getProperty("http.proxyPort");
		return httpHost != null && httpPort != null;
	}

	private OkHttpClient client() {
		String httpHost = System.getProperty("http.proxyHost");
		String httpPort = System.getProperty("http.proxyPort");

		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		if (httpHost != null)
			builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpHost, Integer.parseInt(httpPort))));
		return builder
				.build();
	}
}
