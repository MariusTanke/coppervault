package co.coppervault.app.navigation.tabs

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.FadeTransition
import co.coppervault.app.ui.screens.forum.ForumScreen
import co.coppervault.app.ui.screens.home.HomeScreen
import co.coppervault.app.ui.screens.library.LibraryScreen
import co.coppervault.app.ui.screens.me.MeScreen
import co.coppervault.app.ui.screens.worlds.WorldsScreen
import co.coppervault.app.ui.strings.CVStrings

object HomeTab : Tab {
    override val options: TabOptions
        @Composable get() = TabOptions(index = 0u, title = CVStrings.current.tHome)

    @Composable
    override fun Content() {
        Navigator(HomeScreen()) { FadeTransition(it) }
    }
}

object WorldsTab : Tab {
    override val options: TabOptions
        @Composable get() = TabOptions(index = 1u, title = CVStrings.current.tWorlds)

    @Composable
    override fun Content() {
        Navigator(WorldsScreen()) { FadeTransition(it) }
    }
}

object LibraryTab : Tab {
    override val options: TabOptions
        @Composable get() = TabOptions(index = 2u, title = CVStrings.current.tLibrary)

    @Composable
    override fun Content() {
        Navigator(LibraryScreen()) { FadeTransition(it) }
    }
}

object ForumTab : Tab {
    override val options: TabOptions
        @Composable get() = TabOptions(index = 3u, title = CVStrings.current.tForum)

    @Composable
    override fun Content() {
        Navigator(ForumScreen()) { FadeTransition(it) }
    }
}

object MeTab : Tab {
    override val options: TabOptions
        @Composable get() = TabOptions(index = 4u, title = CVStrings.current.tMe)

    @Composable
    override fun Content() {
        Navigator(MeScreen()) { FadeTransition(it) }
    }
}
