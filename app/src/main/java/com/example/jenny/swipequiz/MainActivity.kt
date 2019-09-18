package com.example.jenny.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        // Initialize the recycler view with a linear layout manager, adapter.
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter

        // Adds a line under each item in the RecyclerView to separate them.
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        // Populate the questions list.
        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i]))
        }
        // Notify that data has changed.
        questionAdapter.notifyDataSetChanged()
    }

}

