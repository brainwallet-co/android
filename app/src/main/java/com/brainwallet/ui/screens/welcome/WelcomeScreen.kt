package com.brainwallet.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.brainwallet.R
import com.brainwallet.navigation.OnNavigate
import com.brainwallet.navigation.Route
import com.brainwallet.navigation.UiEffect
import com.brainwallet.ui.composable.BorderedLargeButton
import com.brainwallet.ui.composable.BrainwalletButton
import com.brainwallet.ui.composable.bottomsheet.FiatSelectorBottomSheet
import com.brainwallet.ui.composable.bottomsheet.LanguageSelectorBottomSheet
import com.brainwallet.ui.theme.BrainwalletTheme

@Composable
fun WelcomeScreen(
    onNavigate: OnNavigate = {},
    viewModel: WelcomeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    var mainBoxFactor = 0.5
    val thirdOfScreenHeight = (screenHeight * mainBoxFactor).toInt()

    //todo: the following sizing can be move to BrainwalletTheme
    // Global layout
    val buttonFontSize = 16
    val thinButtonFontSize = 14
    val iconButtonSize = 32
    val toggleButtonSize = 45
    val leadTrailPadding = 24
    val halfLeadTrailPadding = leadTrailPadding / 2
    val doubleLeadTrailPadding = leadTrailPadding * 2
    val rowPadding = 8
    val tinyPad = 4
    val activeRowHeight = 70

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcomeemoji20250212))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrainwalletTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.weight(0.4f))

        Image(
            painterResource(R.drawable.bw_white_logotype),
            contentDescription = "bw_white_logotype",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                BrainwalletTheme.colors.content,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(doubleLeadTrailPadding.dp)
        )

        // Animation Placeholder
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(thirdOfScreenHeight.dp)
                .padding(leadTrailPadding.dp)
        ) {

            LottieAnimation(
                modifier = Modifier.background(BrainwalletTheme.colors.surface),
                composition = composition,
                contentScale = ContentScale.Fit,
                progress = { progress }
            )
        }
        // TODO: implement later, for now just comment this
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(activeRowHeight.dp)
                .padding(horizontal = halfLeadTrailPadding.dp)
                .padding(vertical = rowPadding.dp),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Spacer(modifier = Modifier.weight(0.1f))

            BrainwalletButton(
                modifier = Modifier.weight(0.9f),
                onClick = {
                    viewModel.onEvent(WelcomeEvent.OnLanguageSelectorButtonClick)
                }
            ) {
                Text(
                    text = state.selectedLanguage.title,
                    fontSize = 14.sp,
                    color = BrainwalletTheme.colors.content
                )
            }

            Spacer(modifier = Modifier.weight(0.2f))

            DarkModeToggleButton(
                modifier = Modifier
                    .width(toggleButtonSize.dp)
                    .aspectRatio(1f),
                checked = state.darkMode,
                onCheckedChange = {
                    viewModel.onEvent(WelcomeEvent.OnToggleDarkMode)
                }
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .width(iconButtonSize.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .border(
                            1.dp,
                            if (state.darkMode) BrainwalletTheme.colors.warn else BrainwalletTheme.colors.surface,
                            CircleShape
                        )
                        .background(if (state.darkMode) BrainwalletTheme.colors.surface else BrainwalletTheme.colors.content)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(iconButtonSize.dp)
                            .aspectRatio(1f),
                        tint = if (state.darkMode) BrainwalletTheme.colors.warn else BrainwalletTheme.colors.surface,
                        painter = painterResource(if (state.darkMode) R.drawable.ic_light_mode else R.drawable.ic_dark_mode),
                        contentDescription = stringResource(R.string.toggle_dark_mode),
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.2f))

            BrainwalletButton(
                modifier = Modifier.weight(0.9f),
                onClick = { viewModel.onEvent(WelcomeEvent.OnFiatButtonClick) }
            ) {
                Text(
                    text = state.selectedCurrency.name,
                    fontSize = 14.sp,
                    color = BrainwalletTheme.colors.content
                )
            }

            Spacer(modifier = Modifier.weight(0.1f))

        }
        // Ready Button
        BorderedLargeButton(
            onClick = {
                onNavigate.invoke(UiEffect.Navigate(Route.Ready))
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(horizontal = halfLeadTrailPadding.dp)
                .padding(vertical = rowPadding.dp)
                .height(activeRowHeight.dp)

        ) {
            Text(
                text = stringResource(R.string.ready),
                fontSize = buttonFontSize.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

        // Restore Button
        BorderedLargeButton(
            onClick = {
                onNavigate.invoke(UiEffect.Navigate(Route.InputWords()))
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(horizontal = halfLeadTrailPadding.dp)
                .padding(vertical = rowPadding.dp)
                .height(activeRowHeight.dp)
                .clip(RoundedCornerShape(50))
        ) {
            Text(
                text = stringResource(R.string.restore),
                fontSize = thinButtonFontSize.sp,
                fontWeight = FontWeight.Thin,
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))
    }

    //language selector
    if (state.languageSelectorBottomSheetVisible) {
        LanguageSelectorBottomSheet(
            selectedLanguage = state.selectedLanguage,
            onLanguageSelect = { language ->
                viewModel.onEvent(
                    WelcomeEvent.OnLanguageChange(
                        language
                    )
                )
            },
            onDismissRequest = {
                viewModel.onEvent(WelcomeEvent.OnLanguageSelectorDismiss)
            },
        )
    }

    //fiat/currency selector
    if (state.fiatSelectorBottomSheetVisible) {
        FiatSelectorBottomSheet(
            selectedCurrency = state.selectedCurrency,
            onFiatSelect = {
                viewModel.onEvent(WelcomeEvent.OnFiatChange(it))
            },
            onDismissRequest = {
                viewModel.onEvent(WelcomeEvent.OnFiatSelectorDismiss)
            }
        )
    }
}

@Composable
fun DarkModeToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        content = content
    )
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}