@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)
package com.brainwallet.ui.screen.setpasscode
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brainwallet.R
import com.brainwallet.ui.composable.LargeButton

//OnSetBiometrics


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetPasscodeConfirmScreen(
    digits: List<Int>,
    viewModel: SetPasscodeMainViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current


    /// Layout values
    val leadingCopyPadding = 16

    val horizontalVerticalSpacing = 8
    val spacerHeight = 90
    val imageMedium = 80

    val headlineFontSize = 44
    val paragraphFontSize = 22
    val lineHeight = 35


    LaunchedEffect(Unit) {
//        viewModel.onEvent(SetPasscodeScreenEvent.OnLoad(digits))
    }

    LaunchedEffect(Unit) {
        ///
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                ),
                title = {
                },
                navigationIcon = {
                    IconButton(
                        onClick = {  },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                }
            )
        }

    ) {
            paddingValues ->

        Spacer(modifier = Modifier.height(spacerHeight.dp))
        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFF2C2C2C)
                )
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(horizontalVerticalSpacing.dp),
        ) {

            Text(
                text = stringResource(R.string.setup_app_passcode),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = headlineFontSize.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(leadingCopyPadding.dp)
            )

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.setup_app_details_1))
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                        )
                    ) {
                        append(stringResource(R.string.setup_app_details_2))
                    }
                    append("\n")
                    append(stringResource(R.string.setup_app_details_3))
                },
                style = MaterialTheme.typography.labelLarge.copy(color = Color.White),
                textAlign = TextAlign.Left,
                lineHeight = lineHeight.sp,
                fontSize = paragraphFontSize.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = leadingCopyPadding.dp)
            )
            Spacer(modifier = Modifier.weight(0.1f))


        }

    }
}

@Preview
@Composable
fun SetPasscodeConfirmScreenPreview() {
    SetPasscodeConfirmScreen(
        digits = TODO(),
        viewModel = TODO()
    )
}



