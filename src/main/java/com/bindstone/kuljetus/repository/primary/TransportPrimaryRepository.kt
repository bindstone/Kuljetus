package com.bindstone.kuljetus.repository.primary

import com.bindstone.kuljetus.domain.Transport
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TransportPrimaryRepository : ReactiveMongoRepository<Transport, String>
