package com.bignerdranch.android.tbnr_geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, var cheater: Boolean)