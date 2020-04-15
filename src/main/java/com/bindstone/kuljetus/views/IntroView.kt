package com.bindstone.kuljetus.views;

import com.bindstone.kuljetus.service.ImportService;
import com.bindstone.kuljetus.views.components.FileReceiver;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

@Route(value = "", layout = MainView.class)
@PageTitle("Introduction")
public class IntroView extends VerticalLayout {
    Logger logger = LoggerFactory.getLogger(IntroView.class);

    public IntroView(ImportService importService) {
        setId("intro-view");
        setSizeFull();

        H2 title = new H2("Initialize Data");

        Paragraph p1 = new Paragraph("You can download the latest XML file download from the link below and upload them by this page");

        String link = "https://data.public.lu/fr/datasets/parc-automobile-du-luxembourg/";
        Anchor anchor = new Anchor(link, link);
        anchor.setTarget("_blank");

        this.add(title, p1, anchor);

        Paragraph p2 = new Paragraph("The upload take about 15 minutes. You can follow the upload also on the mongodb interface for loading about 75000 documents.");

        String linkdb = "http://127.0.0.1:8081/db/transport/";
        Anchor anchordb = new Anchor(linkdb, linkdb);
        anchor.setTarget("_blank");

        this.add(p2, anchordb);

        Paragraph error = new Paragraph();

        Upload upload = new Upload();
        File tempFile = getTempFile();
        FileReceiver fileReceiver = new FileReceiver(tempFile);
        upload.setReceiver(fileReceiver);
        upload.setDropLabel(new Label("Drop"));
        upload.setMaxFiles(1);
        upload.setMaxFileSize(800 * 1024 * 1024);
        upload.setHeight("150px");

        upload.addSucceededListener(event -> {
            try {
                importService.importData(tempFile);
            } catch (Exception e) {
                logger.error("Import error", e);
                error.setText(Arrays
                        .stream(e.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.joining("\n")));
            }
        });

        this.add(upload, error);
    }

    private File getTempFile() {
        try {
            return File.createTempFile("kuljetus", "xml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
