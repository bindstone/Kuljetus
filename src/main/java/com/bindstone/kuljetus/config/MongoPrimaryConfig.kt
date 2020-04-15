package com.bindstone.kuljetus.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackages = ["com.bindstone.kuljetus.repository.primary"], reactiveMongoTemplateRef = "primaryMongoTemplate")
open class MongoPrimaryConfig {
    @Value("\${spring.data.mongodb.primary.url}")
    private val primaryUrl: String? = null

    @Primary
    @Bean(name = ["primaryMongoTemplate"])
    @Throws(Exception::class)
    open fun primaryMongoTemplate(): ReactiveMongoTemplate {
        return ReactiveMongoTemplate(reactiveMongoPrimaryClient(), "transport")
    }

    @Bean
    @Primary
    open fun reactiveMongoPrimaryClient(): MongoClient {
        return MongoClients.create(primaryUrl)
    }
}
