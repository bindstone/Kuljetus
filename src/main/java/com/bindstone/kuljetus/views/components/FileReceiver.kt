package com.bindstone.kuljetus.views.components

import com.vaadin.flow.component.upload.Receiver
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.OutputStream

class FileReceiver(private val file: File) : Receiver {
    var logger = LoggerFactory.getLogger(FileReceiver::class.java)
    override fun receiveUpload(s: String, s1: String): OutputStream {
        logger.info("Stream content for [{}][{}] to[{}]", s, s1, file.absolutePath)
        return try {
            FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            throw RuntimeException("Error creating FileOutputStream", e)
        }
    }

}
