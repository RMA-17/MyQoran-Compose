package com.rmaproject.myqoran.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmaproject.myqoran.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyQoranAppBar(
    goToSearch: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.myqoransvglogo),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .height(28.dp)
                        .padding(end = 8.dp)
                )
                Column {
                    Text(
                        text = "MyQoran",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "by RMA Projects",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Open Navigation Drawer"
                )
            }
        },
        actions = {
            IconButton(onClick = { goToSearch() }) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Navigate to Search Screen"
                )
            }
        },
    )
}



@Preview
@Composable
fun MyQoranAppBarPreview() {
    MyQoranAppBar({}, {})
}