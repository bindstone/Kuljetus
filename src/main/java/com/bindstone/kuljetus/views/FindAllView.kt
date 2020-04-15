package com.bindstone.kuljetus.views

import com.bindstone.kuljetus.domain.Transport
import com.bindstone.kuljetus.service.TransportService
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.data.provider.ListDataProvider
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import org.slf4j.LoggerFactory
import reactor.core.scheduler.Schedulers
import java.time.Duration
import java.util.*

@Route(value = "find-all", layout = MainView::class)
@PageTitle("Find All")
class FindAllView(transportService: TransportService) : Div() {
    private val transportService: TransportService
    private val grid: Grid<Transport>
    var logger = LoggerFactory.getLogger(FindAllView::class.java)
    private val list: ListDataProvider<Transport>

    init {
        setId("find-all-view")
        setSizeFull()
        this.add(Label("This view is a FindAll streamed and returning nearly 75.000 records."))
        this.transportService = transportService
        list = ListDataProvider(ArrayList())
        grid = Grid(Transport::class.java)
        grid.dataProvider = list
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES)
        grid.setHeightFull()
        add(grid)
        transportService.findAll()
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError { throwable: Throwable -> logger.error(throwable.message) }
                .subscribe { transport: Transport ->
                    val ui = this.ui
                    if (ui.isPresent) {
                        ui.get().access {
                            list.items.add(transport)
                            grid.dataProvider.refreshAll()
                        }
                    } else {
                        list.items.add(transport)
                        grid.dataProvider.refreshAll()
                    }
                }
    }
}
