package com.example.jenny.swipequiz

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_quiz.*
import kotlinx.android.synthetic.main.item_quiz.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter.
        rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter

        // Adds a line under each item in the RecyclerView to separate them.
        rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        // Populate the questions list.
        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.SOLUTIONS[i], Question.IDS[i]))
        }
        // Notify that data has changed.
        questionAdapter.notifyDataSetChanged()

        // Attach the ItemTouchHelper to the recycler view.
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    // Create a touch helper to recognize when a user swipes an item from a recycler view.
    private fun createItemTouchHelper(): ItemTouchHelper {
        // User can swipe left or right.
        val callback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                // Callback triggered when a user swiped an item.

                // Enables or Disables the ability to move items up and down.
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                // Callback triggered when a user swiped an item to left.
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    var position = viewHolder.adapterPosition

                    // Pass the direction variable and position to function.
                    checkAnswer(viewHolder, intToBool(direction), position)
                }
            }
        return ItemTouchHelper(callback)
    }

    private fun intToBool(direction: Int): Boolean {
        return direction == ItemTouchHelper.RIGHT
    }

    private fun checkAnswer(viewHolder: RecyclerView.ViewHolder, answer: Boolean, questionPos: Int) {

        var correctText = "correct!"
        var incorrectText = "incorrect..."
        var correctColour =  Color.parseColor("#02d998")
        var incorrectColour = Color.parseColor("#db0240")

        if (questionPos == Question.IDS[questionPos] && answer == Question.SOLUTIONS[questionPos]) {
            // If user's answer does match the solution for the question.
            Toast.makeText(this, getString(R.string.answer, correctText), Toast.LENGTH_SHORT).show()
            viewHolder.itemView.setBackgroundColor(correctColour)
            questionAdapter.notifyDataSetChanged()
        } else {
            // When it does not match.
            Toast.makeText(this, getString(R.string.answer, incorrectText), Toast.LENGTH_SHORT).show()
            viewHolder.itemView.setBackgroundColor(incorrectColour)
            questionAdapter.notifyDataSetChanged()
        }
    }
}



