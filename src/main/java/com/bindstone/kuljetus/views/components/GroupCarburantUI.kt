package com.bindstone.kuljetus.views.components

import com.bindstone.kuljetus.domain.CarburantCount
import com.bindstone.kuljetus.service.TransportService
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.data.provider.ListDataProvider
import org.slf4j.LoggerFactory
import reactor.core.scheduler.Schedulers
import java.time.Duration
import java.util.*

class GroupCarburantUI(transportService: TransportService) : HorizontalLayout() {
    private val grid: Grid<CarburantCount>
    var logger = LoggerFactory.getLogger(GroupCarburantUI::class.java)
    private val list: ListDataProvider<CarburantCount>

    init {
        setId("group-carburant-ui")
        width = "500px"
        height = "200px"
        isMargin = true
        list = ListDataProvider(ArrayList())
        grid = Grid(CarburantCount::class.java)
        grid.setColumns("libelleCarburant", "count")
        grid.dataProvider = list
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES)
        grid.setSizeFull()
        add(grid)
        transportService.countByCarburant
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError { throwable: Throwable -> logger.error(throwable.message) }
                .subscribe { transport: CarburantCount ->
                    logger.info(transport.toString())
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
