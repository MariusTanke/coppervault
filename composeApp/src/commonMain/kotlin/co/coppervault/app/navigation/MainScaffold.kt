package co.coppervault.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import co.coppervault.app.navigation.tabs.ForumTab
import co.coppervault.app.navigation.tabs.HomeTab
import co.coppervault.app.navigation.tabs.LibraryTab
import co.coppervault.app.navigation.tabs.MeTab
import co.coppervault.app.navigation.tabs.WorldsTab
import co.coppervault.app.ui.components.CVTab
import co.coppervault.app.ui.components.CVTabBar

class MainScaffold : Screen {

    @Composable
    override fun Content() {
        TabNavigator(HomeTab) { tabNavigator ->
            Box(Modifier.fillMaxSize()) {
                // Tab content with bottom padding for the bar
                Box(Modifier.fillMaxSize().padding(bottom = 84.dp)) {
                    CurrentTab()
                }

                // Bottom tab bar
                CVTabBar(
                    activeTab = tabToCV(tabNavigator.current),
                    onTabSelected = { cvTab ->
                        tabNavigator.current = cvToTab(cvTab)
                    },
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    }

    private fun tabToCV(tab: Tab): CVTab = when (tab) {
        HomeTab    -> CVTab.Home
        WorldsTab  -> CVTab.Worlds
        LibraryTab -> CVTab.Library
        ForumTab   -> CVTab.Forum
        MeTab      -> CVTab.Me
        else       -> CVTab.Home
    }

    private fun cvToTab(cvTab: CVTab): Tab = when (cvTab) {
        CVTab.Home    -> HomeTab
        CVTab.Worlds  -> WorldsTab
        CVTab.Library -> LibraryTab
        CVTab.Forum   -> ForumTab
        CVTab.Me      -> MeTab
    }
}
