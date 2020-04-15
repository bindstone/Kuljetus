package com.bindstone.kuljetus.views

import com.bindstone.kuljetus.domain.Transport
import com.bindstone.kuljetus.service.TransportService
import com.bindstone.kuljetus.views.MainView
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.provider.ListDataProvider
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import reactor.core.scheduler.Schedulers
import java.time.Duration
import java.util.*

@Route(value = "search", layout = MainView::class)
@PageTitle("Search View")
class SearchView(private val transportService: TransportService) : Div() {
    var logger = LoggerFactory.getLogger(SearchView::class.java)
    private val grid: Grid<Transport>
    private val marque: TextField
    private val couleur: TextField
    private val list: ListDataProvider<Transport>
    private fun search() {
        val query = Query()
        if (Objects.nonNull(marque) && StringUtils.isNotEmpty(marque.value)) {
            query.addCriteria(Criteria.where("libelleMarque").`is`(marque.value))
        }
        if (Objects.nonNull(couleur) && StringUtils.isNotEmpty(couleur.value)) {
            query.addCriteria(Criteria.where("couleur").`is`(couleur.value))
        }
        list.items.clear()
        transportService.find(query)
                .publishOn(Schedulers.immediate())
                .delayElements(Duration.ofSeconds(0))
                .doOnError { throwable: Throwable -> logger.error(throwable.message) }
                .subscribe { transport: Transport ->
                    logger.info("{}", transport)
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

    init {
        setId("search-view")
        setSizeFull()
        val hl = HorizontalLayout()
        marque = TextField()
        marque.placeholder = "Marque"
        couleur = TextField()
        couleur.placeholder = "Couleur"
        val search = Button("Search")
        search.setHeightFull()
        search.addClickListener { buttonClickEvent: ClickEvent<Button?>? -> search() }
        hl.add(marque, couleur, search)
        list = ListDataProvider(ArrayList())
        grid = Grid(Transport::class.java)
        grid.dataProvider = list
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES)
        grid.setSizeFull()
        this.add(hl, grid)
    }
}
