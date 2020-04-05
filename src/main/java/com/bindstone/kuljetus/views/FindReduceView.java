package com.bindstone.kuljetus.views;

import com.bindstone.kuljetus.domain.TransportList;
import com.bindstone.kuljetus.service.TransportService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;

@Route(value = "find-reduce", layout = MainView.class)
@PageTitle("Find Reduce View")
public class FindReduceView extends Div {

    private final TransportService transportService;
    private final Grid<TransportList> grid;
    private ListDataProvider<TransportList> list;

    public FindReduceView(TransportService transportService) {
        setId("find-reduce-view");
        setSizeFull();

        this.add(new Label("This view is a FindAll with reduced columns as streamed and returning nearly 75.000 records."));

        this.transportService = transportService;

        list = new ListDataProvider<>(new ArrayList<>());

        grid = new Grid<>(TransportList.class);
        grid.setColumns("libelleMarque", "designationCommerciale", "couleur", "numeroPVR");
        grid.setDataProvider(list);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setHeightFull();

        HeaderRow filterRow = grid.appendHeaderRow();

        addFilters(filterRow);

        add(grid);

        transportService.getList()
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

    private void addFilters(HeaderRow filterRow) {
        TextField couleurField = new TextField();
        Grid.Column<TransportList> couleur = grid.getColumnByKey("couleur");
        couleurField.addValueChangeListener(event -> list.addFilter(
                transport -> StringUtils.containsIgnoreCase(transport.getCouleur(),
                        couleurField.getValue())));

        filterRow.getCell(couleur).setComponent(couleurField);
        couleurField.setSizeFull();


        TextField libelleField = new TextField();
        Grid.Column<TransportList> libelle = grid.getColumnByKey("libelleMarque");
        libelleField.addValueChangeListener(event -> list.addFilter(
                transport -> StringUtils.containsIgnoreCase(transport.getLibelleMarque(),
                        libelleField.getValue())));

        filterRow.getCell(libelle).setComponent(libelleField);
        libelleField.setSizeFull();


        TextField commercialeField = new TextField();
        Grid.Column<TransportList> commerciale = grid.getColumnByKey("designationCommerciale");
        commercialeField.addValueChangeListener(event -> list.addFilter(
                tranport -> StringUtils.containsIgnoreCase(tranport.getDesignationCommerciale(),
                        commercialeField.getValue())));

        filterRow.getCell(commerciale).setComponent(commercialeField);
        commercialeField.setSizeFull();


        TextField pvrField = new TextField();
        Grid.Column<TransportList> pvr = grid.getColumnByKey("numeroPVR");
        pvrField.addValueChangeListener(event -> list.addFilter(
                transport -> StringUtils.containsIgnoreCase(transport.getNumeroPVR(),
                        pvrField.getValue())));

        filterRow.getCell(pvr).setComponent(pvrField);
        pvrField.setSizeFull();
    }

}
