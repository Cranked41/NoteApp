package com.example.noteapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.data.db.models.Picture

@Dao
interface PictureDao {
    @Query("Select * from picture where noteId=:noteId")
    fun getUserNotes(noteId: Int): List<Picture>

    @Insert
    fun insert(picture: Picture)

    @Update
    fun update(picture: Picture)


}