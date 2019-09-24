package com.example.jenny.swipequiz

data class Question (
    var question: String,
    var solution: Boolean
) {
    companion object {
        val QUESTIONS = arrayOf(
            "A 'var' and 'val' are the same.",
            "Mobile Application Development grants 12 EC.",
            "A Unit in Kotlin corresponds to a void in Java.",
            "In Kotlin 'when' replaces the 'switch' operator in Java."
            )

        val SOLUTIONS = arrayOf(
            false,
            true,
            true,
            true
        )
    }
}