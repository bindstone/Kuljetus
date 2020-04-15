package com.bindstone.kuljetus.views

import com.bindstone.kuljetus.domain.TransportList
import com.bindstone.kuljetus.service.TransportService
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant
import com.vaadin.flow.component.grid.HeaderRow
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.provider.ListDataProvider
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import reactor.core.scheduler.Schedulers
import java.time.Duration
import java.util.*

@Route(value = "find-reduce", layout = MainView::class)
@PageTitle("Find Reduce View")
class FindReduceView(transportService: TransportService) : Div() {
    private val transportService: TransportService
    private val grid: Grid<TransportList>
    var logger = LoggerFactory.getLogger(FindReduceView::class.java)
    private val list: ListDataProvider<TransportList>
    private fun addFilters(filterRow: HeaderRow) {
        val couleurField = TextField()
        val couleur = grid.getColumnByKey("couleur")
        couleurField.addValueChangeListener { event: ComponentValueChangeEvent<TextField?, String?>? ->
            list.addFilter { transport: TransportList ->
                StringUtils.containsIgnoreCase(transport.couleur,
                        couleurField.value)
            }
        }
        filterRow.getCell(couleur).setComponent(couleurField)
        couleurField.setSizeFull()
        val libelleField = TextField()
        val libelle = grid.getColumnByKey("libelleMarque")
        libelleField.addValueChangeListener { event: ComponentValueChangeEvent<TextField?, String?>? ->
            list.addFilter { transport: TransportList ->
                StringUtils.containsIgnoreCase(transport.libelleMarque,
                        libelleField.value)
            }
        }
        filterRow.getCell(libelle).setComponent(libelleField)
        libelleField.setSizeFull()
        val commercialeField = TextField()
        val commerciale = grid.getColumnByKey("designationCommerciale")
        commercialeField.addValueChangeListener { event: ComponentValueChangeEvent<TextField?, String?>? ->
            list.addFilter { tranport: TransportList ->
                StringUtils.containsIgnoreCase(tranport.designationCommerciale,
                        commercialeField.value)
            }
        }
        filterRow.getCell(commerciale).setComponent(commercialeField)
        commercialeField.setSizeFull()
        val pvrField = TextField()
        val pvr = grid.getColumnByKey("numeroPVR")
        pvrField.addValueChangeListener { event: ComponentValueChangeEvent<TextField?, String?>? ->
            list.addFilter { transport: TransportList ->
                StringUtils.containsIgnoreCase(transport.numeroPVR,
                        pvrField.value)
            }
        }
        filterRow.getCell(pvr).setComponent(pvrField)
        pvrField.setSizeFull()
    }

    init {
        setId("find-reduce-view")
        setSizeFull()
        this.add(Label("This view is a FindAll with reduced columns as streamed and returning nearly 75.000 records."))
        this.transportService = transportService
        list = ListDataProvider(ArrayList())
        grid = Grid(TransportList::class.java)
        grid.setColumns("libelleMarque", "designationCommerciale", "couleur", "numeroPVR")
        grid.dataProvider = list
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES)
        grid.setHeightFull()
        val filterRow = grid.appendHeaderRow()
        addFilters(filterRow)
        add(grid)
        transportService.list
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError { throwable: Throwable -> logger.error(throwable.message) }
                .subscribe { transport: TransportList ->
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
