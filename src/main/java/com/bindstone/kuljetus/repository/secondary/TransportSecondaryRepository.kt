package com.bindstone.kuljetus.repository.secondary

import com.bindstone.kuljetus.domain.Transport
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TransportSecondaryRepository : ReactiveMongoRepository<Transport, String>
