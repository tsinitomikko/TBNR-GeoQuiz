package com.bignerdranch.android.tbnr_geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val DEFAULT_CHEAT_COUNT = 3
private const val IS_CHEAT_USED_KEY = "IS_CHEAT_USED_KEY"
private const val CHEAT_COUNT_KEY = "CHEAT_COUNT_KEY"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var isCheater: Boolean
        get() = savedStateHandle[IS_CHEAT_USED_KEY] ?: false
        set(value) = savedStateHandle.set(IS_CHEAT_USED_KEY, value)

    var cheatCount: Int
        get() = savedStateHandle[CHEAT_COUNT_KEY] ?: DEFAULT_CHEAT_COUNT
        set(value) = savedStateHandle.set(CHEAT_COUNT_KEY, value)
}