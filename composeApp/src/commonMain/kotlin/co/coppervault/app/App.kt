package co.coppervault.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.coppervault.app.ui.components.CVButton
import co.coppervault.app.ui.components.CVButtonSize
import co.coppervault.app.ui.components.CVButtonVariant
import co.coppervault.app.ui.components.CVChip
import co.coppervault.app.ui.components.CVCosmereMark
import co.coppervault.app.ui.components.CVEpigraph
import co.coppervault.app.ui.components.CVIcons
import co.coppervault.app.ui.components.CVInput
import co.coppervault.app.ui.components.CVKicker
import co.coppervault.app.ui.components.CVMistBg
import co.coppervault.app.ui.components.CVSpoilerStrip
import co.coppervault.app.ui.components.CVTab
import co.coppervault.app.ui.components.CVTabBar
import co.coppervault.app.ui.components.CVWordmark
import co.coppervault.app.ui.theme.Aurum
import co.coppervault.app.ui.theme.CVTheme
import co.coppervault.app.ui.theme.Fog
import co.coppervault.app.ui.theme.Roshar
import co.coppervault.app.ui.theme.Scadrial
import co.coppervault.app.ui.theme.Sel
import co.coppervault.app.ui.theme.Stone

@Composable
fun App() {
    CVTheme {
        Box(Modifier.fillMaxSize().background(CVTheme.colors.abyss)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 54.dp)
                    .padding(bottom = 84.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                // ── Branding ────────────────────────────
                SectionLabel("Branding")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    CVCosmereMark(size = 56.dp)
                    CVWordmark(size = 28)
                }

                // ── Kickers ─────────────────────────────
                SectionLabel("Kickers")
                CVKicker("The Archive")
                CVKicker("Today's Page", color = Aurum, size = 9)
                CVKicker("Chronology", color = Fog, size = 8)

                // ── Chips ───────────────────────────────
                SectionLabel("Chips")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CVChip("Roshar", accent = Roshar)
                    CVChip("Scadrial", accent = Scadrial)
                    CVChip("Sel", accent = Sel)
                    CVChip("Aurum", accent = Aurum, filled = true)
                }

                // ── Buttons ─────────────────────────────
                SectionLabel("Buttons")
                CVButton("Enter the Cosmere", onClick = {}, variant = CVButtonVariant.Primary, size = CVButtonSize.L, fullWidth = true)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CVButton("Ghost", onClick = {}, variant = CVButtonVariant.Ghost)
                    CVButton("Outline", onClick = {}, variant = CVButtonVariant.Outline)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CVButton("Loading", onClick = {}, loading = true)
                    CVButton("Disabled", onClick = {}, enabled = false)
                }

                // ── Inputs ──────────────────────────────
                SectionLabel("Inputs")
                var email by remember { mutableStateOf("") }
                CVInput(value = email, onValueChange = { email = it }, placeholder = "Email or username", icon = CVIcons.Mail)
                CVInput(value = "", onValueChange = {}, placeholder = "Password", icon = CVIcons.Lock, isPassword = true)
                CVInput(value = "bad", onValueChange = {}, icon = CVIcons.Lock, isError = true, errorHint = "Must be at least 8 characters")

                // ── Spoiler Strip ───────────────────────
                SectionLabel("Spoiler Strip")
                CVSpoilerStrip("Era 2")
                CVSpoilerStrip("SA5 · Stormlight")

                // ── Epigraph ────────────────────────────
                SectionLabel("Epigraph")
                CVEpigraph(
                    quote = "The most important step a man can take is the next one.",
                    attribution = "Dalinar Kholin",
                )

                // ── Mist Background ─────────────────────
                SectionLabel("Mist Background (clipped)")
                CVMistBg(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CVCosmereMark(size = 44.dp)
                    }
                }
            }

            // ── Tab Bar (bottom) ────────────────────
            var activeTab by remember { mutableStateOf(CVTab.Home) }
            CVTabBar(
                activeTab = activeTab,
                onTabSelected = { activeTab = it },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Column {
        HorizontalDivider(thickness = 0.5.dp, color = Stone)
        Spacer(Modifier.height(8.dp))
        Text(
            text = text.uppercase(),
            style = CVTheme.typography.kicker,
            color = Aurum,
        )
    }
}
