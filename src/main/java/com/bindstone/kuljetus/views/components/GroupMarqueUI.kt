package com.bindstone.kuljetus.views.components

import com.bindstone.kuljetus.domain.LibelleMarqueCount
import com.bindstone.kuljetus.service.TransportService
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.data.provider.ListDataProvider
import org.slf4j.LoggerFactory
import reactor.core.scheduler.Schedulers
import java.time.Duration
import java.util.*

class GroupMarqueUI(transportService: TransportService) : HorizontalLayout() {
    private val grid: Grid<LibelleMarqueCount>
    var logger = LoggerFactory.getLogger(GroupMarqueUI::class.java)
    private val list: ListDataProvider<LibelleMarqueCount>

    init {
        setId("group-marque-ui")
        width = "500px"
        height = "200px"
        isMargin = true
        list = ListDataProvider(ArrayList())
        grid = Grid(LibelleMarqueCount::class.java)
        grid.setColumns("libelleMarque", "count")
        grid.dataProvider = list
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES)
        grid.setSizeFull()
        add(grid)
        transportService.countByMarque
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError { throwable: Throwable -> logger.error(throwable.message) }
                .subscribe { transport: LibelleMarqueCount ->
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
