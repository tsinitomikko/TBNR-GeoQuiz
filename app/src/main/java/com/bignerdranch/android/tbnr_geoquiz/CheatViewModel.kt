package com.bignerdranch.android.tbnr_geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val HAS_CHEATED_KEY = "HAS_CHEATED_KEY"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var isCheater: Boolean
        get() = savedStateHandle[HAS_CHEATED_KEY] ?: false
        set(value) = savedStateHandle.set(HAS_CHEATED_KEY, value)

}