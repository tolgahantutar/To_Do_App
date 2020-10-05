package com.tolgahantutar.to_do_app.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.tolgahantutar.to_do_app.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)

object DatabaseModule {
@Singleton
@Provides
fun provideNoteDatabase(
    @ApplicationContext app : Context
)=Room.databaseBuilder(
    app,
    NoteDatabase::class.java,
    "NotesDatabase"
).build()
}