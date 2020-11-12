package com.daniilk87.simplebook.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.daniilk87.simplebook.R
import com.daniilk87.simplebook.data.Note
import com.daniilk87.simplebook.extensions.getColorInt
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.*

class MainAdapter(val onItemClick: ((Note) -> Unit)? = null): RecyclerView.Adapter <MainAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent,false)
    )


    override fun getItemCount() = notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }

    inner class NoteViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(note: Note) = with(itemView as CardView) {
            title.text = note.title
            body.text = note.textNote

            setCardBackgroundColor(note.color.getColorInt(context))

            itemView.setOnClickListener{
                onItemClick?.invoke(note)
            }
        }
    }
}
