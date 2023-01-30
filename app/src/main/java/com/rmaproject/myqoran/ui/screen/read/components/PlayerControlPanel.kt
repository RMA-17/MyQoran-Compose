package com.rmaproject.myqoran.ui.screen.read.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rmaproject.myqoran.data.kotpref.SettingsPreferences
import com.rmaproject.myqoran.ui.screen.read.ReadQoranViewModel

@Composable
fun PlayerControlPanelBottomBar(
    onSkipPrevClick: () -> Unit,
    onSkipNextClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    onStopClick: () -> Unit,
    playType: ReadQoranViewModel.PlayType,
    modifier: Modifier = Modifier,
    currentPlaying: String = "",
    qori: String = SettingsPreferences.currentQoriName.qoriName,
) {
    BottomAppBar(
        modifier = modifier.height(128.dp),
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                text = currentPlaying,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                text = qori,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Row {
                if (playType == ReadQoranViewModel.PlayType.PLAY_ALL) {
                    IconButton(onClick = onSkipPrevClick) {
                        Icon(
                            Icons.Default.SkipPrevious,
                            contentDescription = "Prev Ayah"
                        )
                    }
                }
                IconButton(onClick = onPlayPauseClick) {
                    Icon(
                        if (
                            playType == ReadQoranViewModel.PlayType.PAUSED
                        ) Icons.Default.PlayArrow
                        else Icons.Default.Pause,
                        contentDescription = "Prev Ayah"
                    )
                }
                if (playType == ReadQoranViewModel.PlayType.PLAY_ALL) {
                    IconButton(onClick = onSkipNextClick) {
                        Icon(
                            Icons.Default.SkipNext,
                            contentDescription = "Prev Ayah"
                        )
                    }
                }
                IconButton(onClick = onStopClick) {
                    Icon(
                        Icons.Default.Stop,
                        contentDescription = "Prev Ayah"
                    )
                }
            }
        }
    }
}