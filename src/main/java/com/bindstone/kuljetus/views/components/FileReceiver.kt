package com.bindstone.kuljetus.views.components;

import com.vaadin.flow.component.upload.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileReceiver implements Receiver {
    private final File file;
    Logger logger = LoggerFactory.getLogger(FileReceiver.class);

    public FileReceiver(File file) {
        this.file = file;
    }

    @Override
    public OutputStream receiveUpload(String s, String s1) {
        logger.info("Stream content for [{}][{}] to[{}]", s, s1, file.getAbsolutePath());
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error creating FileOutputStream", e);
        }
    }
}
