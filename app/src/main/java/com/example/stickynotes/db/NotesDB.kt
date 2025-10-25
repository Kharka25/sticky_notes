package com.example.stickynotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NotesDB: RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        @Volatile //prevents any possible race condition in multithreading
        private var INSTANCE: NotesDB? = null;

        fun getInstance(context: Context): NotesDB {
            //Ensures only one thread can execute the block of code at any given time
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        NotesDB::class.java,
                        "notes_db"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}
