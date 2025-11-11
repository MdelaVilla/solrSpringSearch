package com.ejemplo.config;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

    @Value("${solr.url}")
    private String solrUrl;

    @Bean
    public HttpSolrClient solrClient() {
        return new HttpSolrClient.Builder(solrUrl).build();
    }
}
