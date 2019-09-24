package com.example.jenny.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_quiz.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private var round = 0

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
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                // Callback triggered when a user swiped an item.

                // Enables or Disables the ability to move items up and down.
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                // Callback triggered when a user swiped an item.
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    // Pass the direction variable to function.\
                    checkAnswer(intToBool(direction))
                }
            }
        return ItemTouchHelper(callback)
    }

    private fun intToBool(direction: Int): Boolean {
        return direction >= 0
    }

    private fun checkAnswer(answer: Boolean) {

        var correctText = "Correct! :)"
        var incorrectText = "Incorrect... :("

        // User put correct answer.
        if (answer == Question.SOLUTIONS[id]) {
            // Changes the answer variable in strings.xml and then also fill in the %s.
            Toast.makeText(
                this,
                getString(R.string.answer, correctText, Question.SOLUTIONS[round]),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                getString(R.string.answer, incorrectText, Question.SOLUTIONS[round]),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


