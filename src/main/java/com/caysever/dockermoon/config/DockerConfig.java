package com.caysever.dockermoon.config;

import com.caysever.dockermoon.interceptor.CorrelationIdInterceptor;
import com.caysever.dockermoon.interceptor.LogExecutionInterceptor;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class DockerConfig {

    @Bean
    public DockerClient dockerClient() {
        return new DefaultDockerClient("unix:///var/run/docker.sock");
    }

    @Bean
    public WebMvcConfigurerAdapter adapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new CorrelationIdInterceptor());
                registry.addInterceptor(new LogExecutionInterceptor());
            }
        };
    }
}
