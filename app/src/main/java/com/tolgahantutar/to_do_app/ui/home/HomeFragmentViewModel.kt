package com.tolgahantutar.to_do_app.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.to_do_app.db.Note
import com.tolgahantutar.to_do_app.db.NoteDatabase
import kotlinx.coroutines.launch

class HomeFragmentViewModel @ViewModelInject constructor(
    private val noteDatabase: NoteDatabase
) : ViewModel() {

val notes = MutableLiveData<List<Note>>()

   fun getAllNotes(){
       viewModelScope.launch {
         notes.value= noteDatabase.getNoteDao().getAllNotes()
       }
   }
}