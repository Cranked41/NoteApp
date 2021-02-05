package com.example.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.data.db.models.Notes

class NoteAdapter(private val notes: List<Notes>, val context: Context) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rc_note_item, p0, false))
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle?.setText(notes[position].title)
        holder.tvSubject?.setText(notes[position].subject)
        holder.tvDescription?.setText(notes[position].description)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTitle = view.findViewById<TextView>(R.id.rc_txt_title)
        val tvSubject = view.findViewById<TextView>(R.id.rc_txt_subject)
        val tvDescription = view.findViewById<TextView>(R.id.rc_txt_description)
    }
}