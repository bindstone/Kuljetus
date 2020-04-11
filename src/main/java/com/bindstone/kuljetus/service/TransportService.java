package com.bindstone.kuljetus.service;

import com.bindstone.kuljetus.domain.*;
import com.bindstone.kuljetus.repository.primary.TransportPrimaryRepository;
import com.bindstone.kuljetus.repository.secondary.TransportSecondaryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Named;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class TransportService {

    private final TransportPrimaryRepository transportPrimaryRepository;
    private final TransportSecondaryRepository transportSecondaryRepository;
    private final ReactiveMongoTemplate mongoPrimaryTemplate;
    private final ReactiveMongoTemplate mongoSecondaryTemplate;

    private String databaseName = "transport";

    public TransportService(TransportPrimaryRepository transportPrimaryRepository,
                            TransportSecondaryRepository transportSecondaryRepository,
                            @Named("primaryMongoTemplate") ReactiveMongoTemplate mongoPrimaryTemplate,
                            @Named("secondaryMongoTemplate") ReactiveMongoTemplate mongoSecondaryTemplate) {
        this.transportPrimaryRepository = transportPrimaryRepository;
        this.transportSecondaryRepository = transportSecondaryRepository;
        this.mongoPrimaryTemplate = mongoPrimaryTemplate;
        this.mongoSecondaryTemplate = mongoSecondaryTemplate;
    }

    private ReactiveMongoTemplate currentTemplate() {
        if (databaseName.equals("transport")) {
            return this.mongoPrimaryTemplate;
        } else {
            return this.mongoSecondaryTemplate;
        }
    }

    private ReactiveMongoRepository<Transport, String> currentRepository() {
        if (databaseName.equals("transport")) {
            return this.transportPrimaryRepository;
        } else {
            return this.transportSecondaryRepository;
        }
    }

    public Flux<Transport> find(Query query) {
        return currentTemplate().find(query, Transport.class);
    }

    public Flux<Transport> findAll() {
        return currentRepository().findAll();
    }

    public Flux<LibelleMarqueCount> getCountByMarque() {

        Aggregation agg = newAggregation(
                group("libelleMarque").count().as("count"),
                project("count").and("libelleMarque").previousOperation(),
                sort(Sort.Direction.DESC, "count")
        );

        return currentTemplate().aggregate(agg, Transport.class, LibelleMarqueCount.class);
    }

    public Flux<CategorieCount> getCountByCategorie() {

        Aggregation agg = newAggregation(
                group("categorieStatec").count().as("count"),
                project("count").and("categorieStatec").previousOperation(),
                sort(Sort.Direction.DESC, "count")
        );

        return currentTemplate().aggregate(agg, Transport.class, CategorieCount.class);
    }

    public Flux<CarburantCount> getCountByCarburant() {

        Aggregation agg = newAggregation(
                group("libelleCarburant").count().as("count"),
                project("count").and("libelleCarburant").previousOperation(),
                sort(Sort.Direction.DESC, "count")
        );

        return currentTemplate().aggregate(agg, Transport.class, CarburantCount.class);
    }

    public Flux<TransportList> getList() {

        Aggregation agg = newAggregation(
                project("numeroPVR", "libelleMarque", "designationCommerciale", "couleur")
        );

        return currentTemplate().aggregate(agg, Transport.class, TransportList.class);
    }

    public Flux<TransportList> getListPage() {

        Aggregation agg = newAggregation(
                skip(10L),
                limit(10L),
                sort(Sort.by("numeroPVR"))
        );

        project("numeroPVR", "libelleMarque", "designationCommerciale", "couleur");

        return currentTemplate().aggregate(agg, Transport.class, TransportList.class);

    }

    public Mono<Long> countTransports() {
        return currentRepository().count();
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
