package com.bindstone.kuljetus.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainView.class)
@PageTitle("Introduction")
public class IntroView extends Div {

    public IntroView() {
        setId("intro-view");
        setSizeFull();

        this.add(new Label("Hello World"));
    }
}
