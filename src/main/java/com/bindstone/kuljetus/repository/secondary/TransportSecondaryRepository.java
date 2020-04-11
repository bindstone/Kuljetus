package com.bindstone.kuljetus.repository.secondary;

import com.bindstone.kuljetus.domain.Transport;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransportSecondaryRepository extends ReactiveMongoRepository<Transport, String> {
}
