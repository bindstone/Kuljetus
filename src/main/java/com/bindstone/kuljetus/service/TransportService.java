package com.bindstone.kuljetus.service;

import com.bindstone.kuljetus.domain.Transport;
import com.bindstone.kuljetus.domain.TransportCount;
import com.bindstone.kuljetus.domain.TransportList;
import com.bindstone.kuljetus.repository.TransportRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class TransportService {

    private final TransportRepository transportRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public TransportService(TransportRepository transportRepository, ReactiveMongoTemplate mongoTemplate) {
        this.transportRepository = transportRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Flux<Transport> find(Query query) {
        return mongoTemplate.find(query, Transport.class);
    }

    public Flux<Transport> findAll() {
        return transportRepository.findAll();
    }

    public Flux<TransportCount> getMostWantedConstructor() {

        Aggregation agg = newAggregation(
                group("libelleMarque").count().as("count"),
                project("count").and("libelleMarque").previousOperation(),
                sort(Sort.Direction.DESC, "count")
        );

        return mongoTemplate.aggregate(agg, Transport.class, TransportCount.class);
    }

    public Flux<TransportList> getList() {

        Aggregation agg = newAggregation(
                project("numeroPVR", "libelleMarque", "designationCommerciale", "couleur")
        );

        return mongoTemplate.aggregate(agg, Transport.class, TransportList.class);
    }

    public Flux<TransportList> getListPage() {

        Aggregation agg = newAggregation(
                skip(10L),
                limit(10L),
                sort(Sort.by("numeroPVR"))
        );

        project("numeroPVR", "libelleMarque", "designationCommerciale", "couleur");

        return mongoTemplate.aggregate(agg, Transport.class, TransportList.class);

    }

    public Mono<Long> countTransports() {
        return transportRepository.count();
    }
}
