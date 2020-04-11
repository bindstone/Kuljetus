package com.bindstone.kuljetus.config;

import com.bindstone.kuljetus.repository.primary.TransportPrimaryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = TransportPrimaryRepository.class, reactiveMongoTemplateRef = "primaryMongoTemplate")
public class MongoPrimaryConfig {

    @Value("${spring.data.mongodb.primary.url}")
    private String primaryUrl;

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public ReactiveMongoTemplate primaryMongoTemplate() throws Exception {
        return new ReactiveMongoTemplate(reactiveMongoPrimaryClient(), "transport");
    }

    @Bean
    @Primary
    public MongoClient reactiveMongoPrimaryClient() {
        return MongoClients.create(primaryUrl);
    }
}
