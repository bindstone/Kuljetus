package com.bindstone.kuljetus.views

import com.bindstone.kuljetus.service.TransportService
import com.bindstone.kuljetus.views.MainView
import com.bindstone.kuljetus.views.components.GroupCarburantUI
import com.bindstone.kuljetus.views.components.GroupCategorieUI
import com.bindstone.kuljetus.views.components.GroupMarqueUI
import com.bindstone.kuljetus.views.components.TotalTransportsUI
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route(value = "overview", layout = MainView::class)
@PageTitle("overview")
class OverView(transportService: TransportService) : Div() {
    init {
        setId("over-view")
        setSizeFull()
        val databaseLabel = Label(transportService.databaseName)
        val change = Button("SWITCH")
        change.addClickListener { buttonClickEvent: ClickEvent<Button?>? ->
            if (transportService.databaseName == "transport") {
                transportService.databaseName = "test"
                databaseLabel.text = transportService.databaseName
            } else {
                transportService.databaseName = "transport"
                databaseLabel.text = transportService.databaseName
            }
            UI.getCurrent().page.reload()
        }
        val line1 = HorizontalLayout()
        line1.add(change, databaseLabel)
        this.add(line1)
        this.add(TotalTransportsUI(transportService))
        val line3 = HorizontalLayout()
        line3.add(GroupMarqueUI(transportService))
        line3.add(GroupCategorieUI(transportService))
        this.add(line3)
        val line4 = HorizontalLayout()
        line4.add(GroupCarburantUI(transportService))
        this.add(line4)
    }
}
