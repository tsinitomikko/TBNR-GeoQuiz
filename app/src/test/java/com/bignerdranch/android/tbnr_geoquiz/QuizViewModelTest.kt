package com.bignerdranch.android.tbnr_geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert
import org.junit.Test

class QuizViewModelTest {

    @Test
    fun providesExpectedQuestionText() {
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        Assert.assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun wrapsAroundQuestionBank() {
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)
        Assert.assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        Assert.assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun providesTrueAnswerToQuestion() {
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 1))
        val quizViewModel = QuizViewModel(savedStateHandle)
        Assert.assertTrue(quizViewModel.currentQuestionAnswer)
    }

    @Test
    fun providesFalseAnswerToQuestion() {
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 2))
        val quizViewModel = QuizViewModel(savedStateHandle)
        Assert.assertFalse(quizViewModel.currentQuestionAnswer)
    }
}