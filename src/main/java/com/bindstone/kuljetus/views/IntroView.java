package com.bindstone.kuljetus.views;

import com.bindstone.kuljetus.service.ImportService;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
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

        Paragraph p1 = new Paragraph("You can download the latest Excel (XLSX) file download from the link below and upload them by this page");

        String link = "https://data.public.lu/fr/datasets/parc-automobile-du-luxembourg/";
        Anchor anchor = new Anchor(link, link);
        anchor.setTarget("_blank");

        this.add(title, p1, anchor);

        Paragraph error = new Paragraph();

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setDropLabel(new Label("Drop"));
        upload.setMaxFiles(1);
        upload.setMaxFileSize(800 * 1024 * 1024);
        upload.addSucceededListener(event -> {
            try {
                File targetFile = File.createTempFile("kuljetus", "");
                try {
                    logger.info("Write Buffer to [{}]", targetFile.getAbsolutePath());
                    FileUtils.copyInputStreamToFile(buffer.getInputStream(), targetFile);
                    logger.info("Import Data...");
                    importService.importData(targetFile);
                } catch (Exception e) {
                    error.setText(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString)
                            .collect(Collectors.joining("\n")));
                }
                FileUtils.deleteQuietly(targetFile);
            } catch (Exception e) {
                error.setText(Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString)
                        .collect(Collectors.joining("\n")));
            }
        });
        upload.setHeight("150px");

        this.add(upload, error);
    }
}
