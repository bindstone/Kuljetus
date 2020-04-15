package com.bindstone.kuljetus.views.components

import com.bindstone.kuljetus.domain.CategorieCount
import com.bindstone.kuljetus.service.TransportService
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.data.provider.ListDataProvider
import com.vaadin.flow.function.ValueProvider
import org.slf4j.LoggerFactory
import reactor.core.scheduler.Schedulers
import java.time.Duration
import java.util.*

class GroupCategorieUI(transportService: TransportService) : HorizontalLayout() {
    private val grid: Grid<CategorieCount>
    var logger = LoggerFactory.getLogger(GroupCategorieUI::class.java)
    private val list: ListDataProvider<CategorieCount>

    init {
        setId("group-categorie-ui")
        width = "500px"
        height = "200px"
        isMargin = true
        list = ListDataProvider(ArrayList())
        grid = Grid()
        grid.addColumn(ValueProvider<CategorieCount, Any> { categorieCount: CategorieCount -> categorieCount.categorieStatec!!.label }).setHeader("Categorie")
        grid.addColumn(CategorieCount::count).setHeader("Count")
        grid.dataProvider = list
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES)
        grid.setSizeFull()
        add(grid)
        transportService.countByCategorie
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError { throwable: Throwable -> logger.error(throwable.message) }
                .subscribe { transport: CategorieCount ->
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
