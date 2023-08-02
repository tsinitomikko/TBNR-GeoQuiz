package com.bignerdranch.android.tbnr_geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.tbnr_geoquiz.databinding.ActivityMainBinding
import kotlin.math.roundToInt

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true, null),
        Question(R.string.question_oceans, true, null),
        Question(R.string.question_mideast, false, null),
        Question(R.string.question_africa, false, null),
        Question(R.string.question_americas, true, null),
        Question(R.string.question_asia, true, null)
    )

    private var currentIndex = 0
    private var currentScore = 0
    private var currentAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(Bundle?) called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.app_name)

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)

        setEnabledAnswerButtons()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        questionBank[currentIndex].userAnswer = userAnswer

        currentAnswers++

        if (userAnswer == correctAnswer) {
            currentScore++
        }

        setEnabledAnswerButtons(false)

        showScore()
    }

    private fun setEnabledAnswerButtons() {
        val userAnswer = questionBank[currentIndex].userAnswer

        if (userAnswer == null) {
            setEnabledAnswerButtons(true)
        } else {
            setEnabledAnswerButtons(false)
        }
    }

    private fun setEnabledAnswerButtons(isEnabled: Boolean) {
        binding.trueButton.isEnabled = isEnabled
        binding.falseButton.isEnabled = isEnabled
    }

    private fun showScore() {
        if (currentAnswers == questionBank.size) {
            val score = currentScore.toFloat().div(currentAnswers).times(100).roundToInt()

            Toast.makeText(
                this, "Your score: $score%", Toast.LENGTH_SHORT
            ).show()
        }
    }
}