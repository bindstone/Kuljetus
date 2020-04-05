package com.bindstone.kuljetus.views.components;

import com.bindstone.kuljetus.domain.TransportCount;
import com.bindstone.kuljetus.service.TransportService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

public class GroupTransportsUI extends HorizontalLayout {
    private final Grid<TransportCount> grid;
    Logger logger = LoggerFactory.getLogger(GroupTransportsUI.class);
    private ListDataProvider<TransportCount> list;

    public GroupTransportsUI(TransportService transportService) {

        setId("group-transport-ui");
        setWidth("500px");
        setHeight("200px");

        list = new ListDataProvider<>(new ArrayList<>());
        grid = new Grid<>(TransportCount.class);

        grid.setColumns("libelleMarque", "count");
        grid.setDataProvider(list);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSizeFull();

        add(grid);

        transportService.getMostWantedConstructor()
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .subscribe(transport -> {
                    Optional<UI> ui = this.getUI();
                    if (ui.isPresent()) {
                        ui.get().access(() -> {
                            list.getItems().add(transport);
                            this.grid.getDataProvider().refreshAll();
                        });
                    } else {
                        list.getItems().add(transport);
                        this.grid.getDataProvider().refreshAll();
                    }
                });
    }
}
