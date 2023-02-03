package com.rmaproject.myqoran.ui.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaproject.myqoran.components.MyQoranAppBar
import com.rmaproject.myqoran.data.local.entities.Qoran
import com.rmaproject.myqoran.ui.screen.read.components.ItemReadAyah
import com.rmaproject.myqoran.ui.screen.search.components.SearchField
import com.rmaproject.myqoran.ui.screen.search.event.SearchEvent
import com.rmaproject.myqoran.ui.screen.search.event.SearchUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAyahScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val textFieldState = viewModel.searchQuery.value

    LaunchedEffect(textFieldState) {
        viewModel.onEvent(SearchEvent.FindAyah)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MyQoranAppBar(
                currentDestinationTitle = "Search Ayah",
                navigateUp = navigateUp
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                query = textFieldState.text,
                onValueChange = { newValue ->
                    viewModel.onEvent(SearchEvent.EnterQuery(newValue))
                },
                displayHint = textFieldState.isHintVisible,
                onFocusState = { newFocusState ->
                    viewModel.onEvent(SearchEvent.QueryChangeFocus(newFocusState))
                },
            )
            viewModel.searchAyahState.collectAsState(initial = SearchUiState.EmptyQuery)
                .let { state ->
                    val event = state.value
                    SearchAyahContent(
                        state = event
                    )
                }
        }
    }
}

@Composable
fun SearchAyahContent(
    state: SearchUiState<List<Qoran>>
) {
    when (state) {
        is SearchUiState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Tidak ditemukan surat dengan nama \"${state.query}\"",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
        is SearchUiState.EmptyQuery -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Kotak pencarian masih kosong, isi dulu",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                )
            }
        }
        is SearchUiState.NotEmpty -> {
            LazyColumn(
                modifier = Modifier.padding(8.dp),
            ) {
                items(state.data) { qoran ->
                    ItemReadAyah(
                        modifier = Modifier.padding(vertical = 8.dp),
                        ayahText = qoran.ayahText,
                        ayahTranslate = qoran.translation_id,
                        isRead = false
                    )
                }
            }
        }
    }
}