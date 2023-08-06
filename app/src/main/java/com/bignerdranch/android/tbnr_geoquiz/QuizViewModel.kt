package com.bignerdranch.android.tbnr_geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
private const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
private const val CHEAT_COUNT_KEY = "CHEAT_COUNT_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true, cheater = false),
        Question(R.string.question_oceans, answer = true, cheater = false),
        Question(R.string.question_mideast, answer = false, cheater = false),
        Question(R.string.question_africa, answer = false, cheater = false),
        Question(R.string.question_americas, answer = true, cheater = false),
        Question(R.string.question_asia, answer = true, cheater = false)
    )

    var isCheater: Boolean
        get() = savedStateHandle[IS_CHEATER_KEY] ?: false
        set(value) {
            savedStateHandle[IS_CHEATER_KEY] = value
            questionBank[currentIndex].cheater = value
        }

    var cheatCount: Int
        get() = savedStateHandle[CHEAT_COUNT_KEY] ?: DEFAULT_CHEAT_COUNT
        set(value) = savedStateHandle.set(CHEAT_COUNT_KEY, value)

    private var currentIndex: Int
        get() = savedStateHandle[CURRENT_INDEX_KEY] ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val cheater: Boolean
        get() = questionBank[currentIndex].cheater

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}