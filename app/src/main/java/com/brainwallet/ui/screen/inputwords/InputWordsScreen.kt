@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.brainwallet.ui.screen.inputwords

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brainwallet.R
import com.brainwallet.ui.composable.LargeButton
import com.brainwallet.ui.composable.SeedWordItemBox

@Composable
fun InputWordsScreen(
    onEvent: (InputWordsEvent) -> Unit = {},
    viewModel: InputWordsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val focusRequester = remember { FocusRequester() }


    /// Layout values
    val columnPadding = 16
    val horizontalVerticalSpacing = 8
    val spacerHeight = 48
    val maxItemsPerRow = 3

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { onEvent.invoke(InputWordsEvent.OnBackClick) },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(columnPadding.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(horizontalVerticalSpacing.dp),
        ) {
            Text(
                text = stringResource(R.string.restore_your_power),
                style = MaterialTheme.typography.headlineSmall,
            )

            Text(
                text = stringResource(R.string.restore_your_power_desc),
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(spacerHeight.dp))

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(horizontalVerticalSpacing.dp),
                verticalArrangement = Arrangement.spacedBy(horizontalVerticalSpacing.dp),
                maxItemsInEachRow = maxItemsPerRow
            ) {

                state.seedWords.entries.forEach { (index, text) ->

                    val modifier = if (index == 0)
                        Modifier
                            .focusRequester(focusRequester)
                            .weight(1f)
                    else Modifier.weight(1f)

                    BasicTextField(
                        modifier = modifier,
                        value = text,
                        textStyle = LocalTextStyle.current.copy(
                            color = Color.White
                        ),
                        cursorBrush = androidx.compose.ui.graphics.SolidColor(Color.White),
                        onValueChange = {
                            viewModel.onEvent(
                                InputWordsEvent.OnSeedWordItemChange(
                                    index = index, text = it,
                                )
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = if (index < 11) ImeAction.Next else ImeAction.Done
                        ),
                        decorationBox = { innerTextField ->
                            SeedWordItemBox {
                                Box(
                                    modifier = Modifier.padding(vertical = 8.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.Bottom

                                    ) {
                                        Text("${index + 1}")
                                        innerTextField.invoke()
                                    }
                                }
                            }
                        }
                    )

                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            FilledTonalButton(
                onClick = {
                    viewModel.onEvent(InputWordsEvent.OnClearSeedWords)
                    focusRequester.requestFocus()
                },
            ) {
                Text(stringResource(R.string.clear))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 42.dp),
                text = stringResource(R.string.dont_guess_desc),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.blockchain_litecoin),
                style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
            )

            Spacer(modifier = Modifier.weight(1f))

            LargeButton(
                onClick = {

                },
            ) {
                Text(
                    text = stringResource(R.string.restore_my_brainwallet),
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White) //for now just hardcoded, need to create button composable later and adjust the theme later at [com.brainwallet.ui.theme.Theme]
                )
            }
        }
    }
}