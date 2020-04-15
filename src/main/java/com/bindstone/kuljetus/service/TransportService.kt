package com.bindstone.kuljetus.service

import com.bindstone.kuljetus.domain.*
import com.bindstone.kuljetus.repository.primary.TransportPrimaryRepository
import com.bindstone.kuljetus.repository.secondary.TransportSecondaryRepository
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.inject.Named

@Service
class TransportService(private val transportPrimaryRepository: TransportPrimaryRepository,
                       private val transportSecondaryRepository: TransportSecondaryRepository,
                       @param:Named("primaryMongoTemplate") private val mongoPrimaryTemplate: ReactiveMongoTemplate,
                       @param:Named("secondaryMongoTemplate") private val mongoSecondaryTemplate: ReactiveMongoTemplate) {
    var databaseName = "transport"
    private fun currentTemplate(): ReactiveMongoTemplate {
        return if (databaseName == "transport") {
            mongoPrimaryTemplate
        } else {
            mongoSecondaryTemplate
        }
    }

    private fun currentRepository(): ReactiveMongoRepository<Transport, String> {
        return if (databaseName == "transport") {
            transportPrimaryRepository
        } else {
            transportSecondaryRepository
        }
    }

    fun find(query: Query?): Flux<Transport> {
        return currentTemplate().find(query, Transport::class.java)
    }

    fun findAll(): Flux<Transport> {
        return currentRepository().findAll()
    }

    val countByMarque: Flux<LibelleMarqueCount>
        get() {
            val agg = Aggregation.newAggregation(
                    Aggregation.group("libelleMarque").count().`as`("count"),
                    Aggregation.project("count").and("libelleMarque").previousOperation(),
                    Aggregation.sort(Sort.Direction.DESC, "count")
            )
            return currentTemplate().aggregate(agg, Transport::class.java, LibelleMarqueCount::class.java)
        }

    val countByCategorie: Flux<CategorieCount>
        get() {
            val agg = Aggregation.newAggregation(
                    Aggregation.group("categorieStatec").count().`as`("count"),
                    Aggregation.project("count").and("categorieStatec").previousOperation(),
                    Aggregation.sort(Sort.Direction.DESC, "count")
            )
            return currentTemplate().aggregate(agg, Transport::class.java, CategorieCount::class.java)
        }

    val countByCarburant: Flux<CarburantCount>
        get() {
            val agg = Aggregation.newAggregation(
                    Aggregation.group("libelleCarburant").count().`as`("count"),
                    Aggregation.project("count").and("libelleCarburant").previousOperation(),
                    Aggregation.sort(Sort.Direction.DESC, "count")
            )
            return currentTemplate().aggregate(agg, Transport::class.java, CarburantCount::class.java)
        }

    val list: Flux<TransportList>
        get() {
            val agg = Aggregation.newAggregation(
                    Aggregation.project("numeroPVR", "libelleMarque", "designationCommerciale", "couleur")
            )
            return currentTemplate().aggregate(agg, Transport::class.java, TransportList::class.java)
        }

    val listPage: Flux<TransportList>
        get() {
            val agg = Aggregation.newAggregation(
                    Aggregation.skip(10L),
                    Aggregation.limit(10L),
                    Aggregation.sort(Sort.by("numeroPVR"))
            )
            Aggregation.project("numeroPVR", "libelleMarque", "designationCommerciale", "couleur")
            return currentTemplate().aggregate(agg, Transport::class.java, TransportList::class.java)
        }

    fun countTransports(): Mono<Long> {
        return currentRepository().count()
    }

}
