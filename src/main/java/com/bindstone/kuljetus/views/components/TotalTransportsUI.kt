package com.bindstone.kuljetus.views.components;

import com.bindstone.kuljetus.service.TransportService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TotalTransportsUI extends HorizontalLayout {
    Logger logger = LoggerFactory.getLogger(TotalTransportsUI.class);

    public TotalTransportsUI(TransportService transportService) {
        setId("total-transport-ui");

        Label label = new Label("Count of Transports:");

        this.add(label);

        transportService.countTransports().subscribe(count -> {
            logger.debug("Count Transport [{}]", count);
            Optional<UI> ui = this.getUI();
            if (ui.isPresent()) {
                ui.get().access(() -> {
                    label.setText("Count of Transports: " + count);
                });
            } else {
                label.setText("Count of Transports: " + count);
            }
        });
    }
}
