package com.bindstone.kuljetus.service;

import com.bindstone.kuljetus.repository.primary.TransportPrimaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;

@Service
public class ImportService {
    private final TransportPrimaryRepository transportPrimaryRepository;
    Logger logger = LoggerFactory.getLogger(ImportService.class);

    public ImportService(TransportPrimaryRepository transportPrimaryRepository) {
        this.transportPrimaryRepository = transportPrimaryRepository;
    }

    public void importData(File file) throws FileNotFoundException, XMLStreamException {
        logger.info("Start import data:");
        Instant start = Instant.now();
        new TransportFlux(file)
                .getData()
                // .delayElements(Duration.ofMillis(100)) Backpressure Option
                .flatMap(transportPrimaryRepository::save)
                .subscribe(transport -> logger.info("saved Transport with ID: " + transport.getNumeroPVR()));

        Instant end = Instant.now();

        Duration interval = Duration.between(start, end);
        logger.info("Execution time in seconds: " + interval.getSeconds());
    }

}
