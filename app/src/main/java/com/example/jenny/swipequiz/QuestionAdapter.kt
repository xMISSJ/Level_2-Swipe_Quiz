package com.example.jenny.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// An adapter is created to know how and which items to display.
class QuestionAdapter(private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {



    // Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    // Returns the size of the list
    override fun getItemCount(): Int {
        return questions.size
    }


    // Called by RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    // ViewHolder with a bind method which binds the Question String to a TextView.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvQuestions: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(question: Question) {
            tvQuestions.text = question.question
        }

    }
}



