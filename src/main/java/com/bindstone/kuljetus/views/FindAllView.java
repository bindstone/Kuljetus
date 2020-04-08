package com.bindstone.kuljetus.views;

import com.bindstone.kuljetus.domain.Transport;
import com.bindstone.kuljetus.service.TransportService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

@Route(value = "find-all", layout = MainView.class)
@PageTitle("Find All")
public class FindAllView extends Div {
    private final TransportService transportService;
    private final Grid<Transport> grid;
    Logger logger = LoggerFactory.getLogger(FindAllView.class);
    private ListDataProvider<Transport> list;

    public FindAllView(TransportService transportService) {
        setId("find-all-view");
        setSizeFull();

        this.add(new Label("This view is a FindAll streamed and returning nearly 75.000 records."));

        this.transportService = transportService;

        list = new ListDataProvider<>(new ArrayList<>());

        grid = new Grid<>(Transport.class);
        grid.setDataProvider(list);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setHeightFull();

        add(grid);

        transportService.findAll()
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError(throwable -> logger.error(throwable.getMessage()))
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
