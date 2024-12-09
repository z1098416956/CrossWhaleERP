package com.neton.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.neton.properties.ElasticSearchProperties;
import jakarta.annotation.Resource;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ElasticsearchConfig{

    @Autowired
    private ElasticSearchProperties elasticSearchProperties;
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        // 创建 RestClient
        HttpHost[] hosts = Arrays.stream(elasticSearchProperties.getHostAndPorts())
                .map(node -> {
                    String[] parts = node.split(":");
                    String host = parts[0];
                    int port = Integer.parseInt(parts[1]);
                    return new HttpHost(host, port, "http");
                })
                .toArray(HttpHost[]::new);

        RestClientBuilder builder = RestClient.builder(hosts);
        // 创建 RestClientTransport
        RestClientTransport transport = new RestClientTransport(
                builder.build(),
                new co.elastic.clients.json.jackson.JacksonJsonpMapper()
        );

        // 创建 ElasticsearchClient
        return new ElasticsearchClient(transport);
    }



}
