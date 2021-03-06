package com.bindstone.kuljetus.views.components;

import com.bindstone.kuljetus.domain.CategorieCount;
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

public class GroupCategorieUI extends HorizontalLayout {
    private final Grid<CategorieCount> grid;
    Logger logger = LoggerFactory.getLogger(GroupCategorieUI.class);
    private ListDataProvider<CategorieCount> list;

    public GroupCategorieUI(TransportService transportService) {

        setId("group-categorie-ui");
        setWidth("500px");
        setHeight("200px");
        setMargin(true);

        list = new ListDataProvider<>(new ArrayList<>());
        grid = new Grid<>();

        grid.addColumn(categorieCount -> categorieCount.getCategorieStatec().getLabel()).setHeader("Categorie");
        grid.addColumn(CategorieCount::getCount).setHeader("Count");

        grid.setDataProvider(list);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSizeFull();

        add(grid);

        transportService.getCountByCategorie()
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
