package com.bindstone.kuljetus.repository;

import com.bindstone.kuljetus.domain.Transport;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransportRepository extends ReactiveMongoRepository<Transport, String> {
}
