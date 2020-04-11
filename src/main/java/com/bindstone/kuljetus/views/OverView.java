package com.bindstone.kuljetus.views;

import com.bindstone.kuljetus.service.TransportService;
import com.bindstone.kuljetus.views.components.GroupCarburantUI;
import com.bindstone.kuljetus.views.components.GroupCategorieUI;
import com.bindstone.kuljetus.views.components.GroupMarqueUI;
import com.bindstone.kuljetus.views.components.TotalTransportsUI;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "overview", layout = MainView.class)
@PageTitle("overview")
public class OverView extends Div {

    public OverView(TransportService transportService) {

        setId("over-view");
        setSizeFull();

        Label databaseLabel = new Label(transportService.getDatabaseName());

        Button change = new Button("SWITCH");
        change.addClickListener(buttonClickEvent -> {
            if (transportService.getDatabaseName().equals("transport")) {
                transportService.setDatabaseName("test");
                databaseLabel.setText(transportService.getDatabaseName());
            } else {
                transportService.setDatabaseName("transport");
                databaseLabel.setText(transportService.getDatabaseName());
            }
            UI.getCurrent().getPage().reload();
        });
        HorizontalLayout line1 = new HorizontalLayout();
        line1.add(change, databaseLabel);
        this.add(line1);

        this.add(new TotalTransportsUI(transportService));

        HorizontalLayout line3 = new HorizontalLayout();
        line3.add(new GroupMarqueUI(transportService));
        line3.add(new GroupCategorieUI(transportService));

        this.add(line3);

        HorizontalLayout line4 = new HorizontalLayout();
        line4.add(new GroupCarburantUI(transportService));

        this.add(line4);
    }
}
