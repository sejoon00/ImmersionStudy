package com.project.immersionstudy.kotest

class LengthCalculator(
    val text: String
) {

    fun getLength(): Int {
        return text.length
    }

    companion object {
        fun of(text: String): LengthCalculator {
            return LengthCalculator(text.trim())
        }
    }
}