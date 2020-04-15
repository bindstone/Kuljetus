package com.bindstone.kuljetus.views

import com.bindstone.kuljetus.service.ImportService
import com.bindstone.kuljetus.views.components.FileReceiver
import com.vaadin.flow.component.html.Anchor
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.html.Paragraph
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.upload.SucceededEvent
import com.vaadin.flow.component.upload.Upload
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*
import java.util.stream.Collectors

@Route(value = "", layout = MainView::class)
@PageTitle("Introduction")
class IntroView(importService: ImportService) : VerticalLayout() {
    var logger = LoggerFactory.getLogger(IntroView::class.java)
    private val tempFile: File
        private get() = try {
            File.createTempFile("kuljetus", "xml")
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    init {
        setId("intro-view")
        setSizeFull()
        val title = H2("Initialize Data")
        val p1 = Paragraph("You can download the latest XML file download from the link below and upload them by this page")
        val link = "https://data.public.lu/fr/datasets/parc-automobile-du-luxembourg/"
        val anchor = Anchor(link, link)
        anchor.setTarget("_blank")
        this.add(title, p1, anchor)
        val p2 = Paragraph("The upload take about 15 minutes. You can follow the upload also on the mongodb interface for loading about 75000 documents.")
        val linkdb = "http://127.0.0.1:8081/db/transport/"
        val anchordb = Anchor(linkdb, linkdb)
        anchor.setTarget("_blank")
        this.add(p2, anchordb)
        val error = Paragraph()
        val upload = Upload()
        val tempFile = tempFile
        val fileReceiver = FileReceiver(tempFile)
        upload.receiver = fileReceiver
        upload.dropLabel = Label("Drop")
        upload.maxFiles = 1
        upload.maxFileSize = 800 * 1024 * 1024
        upload.height = "150px"
        upload.addSucceededListener { event: SucceededEvent? ->
            try {
                importService.importData(tempFile)
            } catch (e: Exception) {
                logger.error("Import error", e)
                error.text = Arrays
                        .stream(e.stackTrace)
                        .map { obj: StackTraceElement -> obj.toString() }
                        .collect(Collectors.joining("\n"))
            }
        }
        this.add(upload, error)
    }
}
