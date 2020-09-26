package com.nextlevel.monitoring.backend.application;

import com.nextlevel.monitoring.backend.core.rest.RestHelper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ConditionalOnProperty(value = "keycloak.enabled", havingValue = "false")
@EntityScan(basePackages = "com.nextlevel.monitoring")
@EnableJpaRepositories(basePackages = "com.nextlevel.monitoring")
@EnableWebMvc
@ComponentScan(basePackages = "com.nextlevel.monitoring")
@EnableAspectJAutoProxy(proxyTargetClass = true)

public class WebappConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private com.nextlevel.monitoring.backend.config.Configuration configuration;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Bean
    public RestTemplate restTemplate() {

        final int readTimeout = 1800000; // 30min
        final int connectTimeout = 1800000; // 30min

        /*
         *  The response body is a stream and if you read it in your interceptor it won’t be available for RestTemplate
         *  to deserialize it into your object model. In other words, when you call restTemplate.get… you’ll always get
         *  back empty objects .You can fix that by using a BufferingClientHttpRequestFactory.
         */
        final ClientHttpRequestFactory factory =
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

        final RestTemplate restTemplate = new RestTemplate(factory);
        final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.useSystemProperties();

        final CloseableHttpClient client = clientBuilder.build();

        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(client);
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setConnectTimeout(connectTimeout);

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;

    }

}
