package com.bindstone.kuljetus.views.components

import com.bindstone.kuljetus.service.TransportService
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import org.slf4j.LoggerFactory

class TotalTransportsUI(transportService: TransportService) : HorizontalLayout() {
    var logger = LoggerFactory.getLogger(TotalTransportsUI::class.java)

    init {
        setId("total-transport-ui")
        val label = Label("Count of Transports:")
        this.add(label)
        transportService.countTransports().subscribe { count: Long ->
            logger.debug("Count Transport [{}]", count)
            val ui = this.ui
            if (ui.isPresent) {
                ui.get().access { label.text = "Count of Transports: $count" }
            } else {
                label.text = "Count of Transports: $count"
            }
        }
    }
}
