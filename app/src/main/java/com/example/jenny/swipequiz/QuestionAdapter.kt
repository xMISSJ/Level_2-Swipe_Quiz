package com.example.jenny.swipequiz

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter (private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    private val tvQuestion: TextView = itemView.findViewById(android.R.id.text1)

    fun bind(reminder: Question) {
        tvQuestion.text = question.question
    }


}
