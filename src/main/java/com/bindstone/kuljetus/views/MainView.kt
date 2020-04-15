package com.bindstone.kuljetus.views

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.HasComponents
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.dependency.JsModule
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.tabs.Tab
import com.vaadin.flow.component.tabs.Tabs
import com.vaadin.flow.component.tabs.TabsVariant
import com.vaadin.flow.router.RouteConfiguration
import com.vaadin.flow.router.RouterLink
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo
import java.util.*

@JsModule("./styles/shared-styles.js")
@Theme(value = Lumo::class, variant = Lumo.LIGHT)
class MainView : AppLayout() {
    private val menu: Tabs
    override fun afterNavigation() {
        super.afterNavigation()
        selectTab()
    }

    private fun selectTab() {
        val target = RouteConfiguration.forSessionScope().getUrl(content.javaClass)
        val tabToSelect = menu.children.filter { tab: Component ->
            val child = tab.children.findFirst().get()
            child is RouterLink && child.href == target
        }.findFirst()
        tabToSelect.ifPresent { tab: Component? -> menu.selectedTab = tab as Tab? }
    }

    companion object {
        private fun createMenuTabs(): Tabs {
            val tabs = Tabs()
            tabs.orientation = Tabs.Orientation.VERTICAL
            tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL)
            tabs.setId("tabs")
            tabs.add(*availableTabs)
            return tabs
        }

        private val availableTabs: Array<Tab>
            private get() {
                val tabs: MutableList<Tab> = ArrayList()
                tabs.add(createTab("Introduction", IntroView::class.java))
                tabs.add(createTab("Overview", OverView::class.java))
                tabs.add(createTab("Find Reduce View", FindReduceView::class.java))
                tabs.add(createTab("Find All View", FindAllView::class.java))
                tabs.add(createTab("Search View", SearchView::class.java))
                return tabs.toTypedArray()
            }

        private fun createTab(title: String, viewClass: Class<out Component>): Tab {
            return createTab(populateLink(RouterLink(null, viewClass), title))
        }

        private fun createTab(content: Component): Tab {
            val tab = Tab()
            tab.add(content)
            return tab
        }

        private fun <T : HasComponents?> populateLink(a: T, title: String): T {
            a!!.add(title)
            return a
        }
    }

    init {
        setId("main-view")
        primarySection = Section.DRAWER
        addToNavbar(true, DrawerToggle())
        addToNavbar(false, H2("Kuljetus (Transport)"))
        menu = createMenuTabs()
        addToDrawer(menu)
    }
}
