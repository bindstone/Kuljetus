package com.bindstone.kuljetus.views;

import com.bindstone.kuljetus.domain.Transport;
import com.bindstone.kuljetus.service.TransportService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Route(value = "search", layout = MainView.class)
@PageTitle("Search View")
public class SearchView extends Div {
    Logger logger = LoggerFactory.getLogger(SearchView.class);
    private final TransportService transportService;
    private Grid<Transport> grid;
    private TextField marque;
    private TextField couleur;
    private ListDataProvider<Transport> list;

    public SearchView(TransportService transportService) {
        this.transportService = transportService;

        setId("search-view");
        setSizeFull();

        HorizontalLayout hl = new HorizontalLayout();

        marque = new TextField();
        marque.setPlaceholder("Marque");

        couleur = new TextField();
        couleur.setPlaceholder("Couleur");

        Button search = new Button("Search");
        search.setHeightFull();
        search.addClickListener(buttonClickEvent -> search());

        hl.add(marque, couleur, search);

        list = new ListDataProvider<>(new ArrayList<>());

        grid = new Grid<>(Transport.class);
        grid.setDataProvider(list);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSizeFull();

        this.add(hl, grid);
    }

    private void search() {
        Query query = new Query();
        if (Objects.nonNull(marque) && StringUtils.isNotEmpty(marque.getValue())) {
            query.addCriteria(Criteria.where("libelleMarque").is(marque.getValue()));
        }
        if (Objects.nonNull(couleur) && StringUtils.isNotEmpty(couleur.getValue())) {
            query.addCriteria(Criteria.where("couleur").is(couleur.getValue()));
        }
        list.getItems().clear();

        transportService.find(query)
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .subscribe(transport -> {
                    logger.info("{}", transport);
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
