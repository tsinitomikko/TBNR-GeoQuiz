package com.bignerdranch.android.tbnr_geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.tbnr_geoquiz.databinding.ActivityCheatBinding

const val EXTRA_CHEAT_COUNT = "com.bignerdranch.android.tbnr_geoquiz.cheat_count"
const val EXTRA_IS_CHEATER = "com.bignerdranch.android.tbnr_geoquiz.is_cheater"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.tbnr_geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding

    private val cheatViewModel: CheatViewModel by viewModels()

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        cheatViewModel.isCheater = intent.getBooleanExtra(EXTRA_IS_CHEATER, false)
        cheatViewModel.cheatCount = intent.getIntExtra(EXTRA_CHEAT_COUNT, DEFAULT_CHEAT_COUNT)

        val answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }

        binding.apiLevelTextView.text =
            String.format(resources.getString(R.string.api_level), Build.VERSION.SDK_INT)

        binding.showAnswerButton.setOnClickListener {
            binding.answerTextView.setText(answerText)
            cheatViewModel.isCheater = true
            binding.showAnswerButton.isEnabled = false

            val cheatCount = --cheatViewModel.cheatCount
            showCheatToast(cheatCount)
            setAnswerShownResult(cheatCount)
        }

        if (cheatViewModel.isCheater) {
            binding.answerTextView.setText(answerText)
            binding.showAnswerButton.isEnabled = false
        }

        if (cheatViewModel.cheatCount == 0) {
            binding.showAnswerButton.isEnabled = false
            showCheatToast(cheatViewModel.cheatCount)
        }
    }

    private fun setAnswerShownResult(cheatCount: Int) {
        val data = Intent().apply {
            putExtra(EXTRA_IS_CHEATER, true)
            putExtra(EXTRA_CHEAT_COUNT, cheatCount)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(
            packageContext: Context,
            answerIsTrue: Boolean,
            isCheater: Boolean,
            cheatCount: Int
        ): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                putExtra(EXTRA_IS_CHEATER, isCheater)
                putExtra(EXTRA_CHEAT_COUNT, cheatCount)
            }
        }
    }

    private fun showCheatToast(cheatCount: Int) {
        Toast.makeText(
            this@CheatActivity,
            String.format(resources.getString(R.string.cheats_left), cheatCount),
            Toast.LENGTH_SHORT
        ).show()
    }
}