package com.bignerdranch.android.tbnr_geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.tbnr_geoquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.tbnr_geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.tbnr_geoquiz.answer_is_true"
private const val EXTRA_IS_CHEATER = "com.bignerdranch.android.tbnr_geoquiz.is_cheater"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding

    private var answerIsTrue = false
    private var isCheater = false

    private val cheatViewModel: CheatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        isCheater = intent.getBooleanExtra(EXTRA_IS_CHEATER, false)

        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }

        binding.showAnswerButton.setOnClickListener {
            binding.answerTextView.setText(answerText)
            cheatViewModel.isCheater = true
            setAnswerShownResult(true)
        }

        if (isCheater) {
            binding.answerTextView.setText(answerText)
        }

        if (savedInstanceState != null) {
            setAnswerShownResult(cheatViewModel.isCheater)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean, isCheater: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                putExtra(EXTRA_IS_CHEATER, isCheater)
            }
        }
    }
}