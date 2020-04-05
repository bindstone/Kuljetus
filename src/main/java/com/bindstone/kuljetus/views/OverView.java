package com.bindstone.kuljetus.views;

import com.bindstone.kuljetus.service.TransportService;
import com.bindstone.kuljetus.views.components.GroupTransportsUI;
import com.bindstone.kuljetus.views.components.TotalTransportsUI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "overview", layout = MainView.class)
@PageTitle("overview")
public class OverView extends Div {

    public OverView(TransportService transportService) {

        setId("over-view");
        setSizeFull();

        this.add(new TotalTransportsUI(transportService));
        this.add(new GroupTransportsUI(transportService));
    }
}
