package com.brainwallet.ui.screens.yourseedproveit

import androidx.lifecycle.ViewModel
import com.brainwallet.ui.BrainwalletViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class YourSeedProveItViewModel : BrainwalletViewModel<YourSeedProveItEvent>() {

    private val _state = MutableStateFlow(YourSeedProveItState())
    val state: StateFlow<YourSeedProveItState> = _state.asStateFlow()

    //
    override fun onEvent(event: YourSeedProveItEvent) {
        when (event) {
            is YourSeedProveItEvent.OnLoad -> _state.update {
                it.copy(
                    correctSeedWords = event.seedWords.associateWith { "" },
                    shuffledSeedWords = event.seedWords.shuffled()
                )
            }

            is YourSeedProveItEvent.OnDropSeedWordItem -> _state.update {
                val correctSeedWords = it.correctSeedWords.toMutableMap().apply {
                    this[event.expectedWord] = event.actualWord
                }

                it.copy(
                    correctSeedWords = correctSeedWords,
                    orderCorrected = correctSeedWords.all { (expectedWord, actualWord) -> expectedWord == actualWord }
                )
            }

            YourSeedProveItEvent.OnClear -> _state.update {
                it.copy(
                    correctSeedWords = it.correctSeedWords.mapValues { "" }
                )
            }

            else -> Unit
        }
    }
}