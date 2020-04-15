package com.bindstone.kuljetus.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.bindstone.kuljetus.repository.secondary"], reactiveMongoTemplateRef = "secondaryMongoTemplate")
open class MongoSecondaryConfig {
    @Value("\${spring.data.mongodb.secondary.url}")
    private val secondaryUrl: String? = null

    @Bean(name = ["secondaryMongoTemplate"])
    @Throws(Exception::class)
    open fun secondaryMongoTemplate(): ReactiveMongoTemplate {
        return ReactiveMongoTemplate(reactiveMongoSecondaryClient(), "backup")
    }

    @Bean
    open fun reactiveMongoSecondaryClient(): MongoClient {
        return MongoClients.create(secondaryUrl)
    }
}
