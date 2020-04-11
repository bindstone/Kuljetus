package com.bindstone.kuljetus.config;

import com.bindstone.kuljetus.repository.secondary.TransportSecondaryRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = TransportSecondaryRepository.class, reactiveMongoTemplateRef = "secondaryMongoTemplate")
public class MongoSecondaryConfig {

    @Value("${spring.data.mongodb.secondary.url}")
    private String secondaryUrl;

    @Bean(name = "secondaryMongoTemplate")
    public ReactiveMongoTemplate secondaryMongoTemplate() throws Exception {
        return new ReactiveMongoTemplate(reactiveMongoSecondaryClient(), "backup");
    }

    @Bean
    public MongoClient reactiveMongoSecondaryClient() {
        return MongoClients.create(secondaryUrl);
    }
}
