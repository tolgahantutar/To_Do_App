package com.tolgahantutar.to_do_app.ui.addnote


import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.tolgahantutar.to_do_app.db.Note
import com.tolgahantutar.to_do_app.db.NoteDatabase
import kotlinx.coroutines.launch
import java.util.*


class AddNoteFragmentViewModel @ViewModelInject constructor(
    private val noteDatabase: NoteDatabase

) : ViewModel()  {
    val isNoteSaved =MutableLiveData<Boolean>()
    val isNoteUpdated = MutableLiveData<Boolean>()
    val isDeleted = MutableLiveData<Boolean>()

fun saveNote(note: Note?,title:String,duration:String,price:Double,noteString: String){
    isNoteSaved.value=false
    isNoteUpdated.value = false
    viewModelScope.launch { 
         val mNote = Note(title, duration, price, noteString)
        if(note == null){
            noteDatabase.getNoteDao().addNote(mNote)
            isNoteSaved.value= true
        }else{
            mNote.Id = note!!.Id
            noteDatabase.getNoteDao().updateNote(mNote)
            isNoteUpdated.value=true
        }

    }
}
    fun deleteNote(note: Note?){
        isDeleted.value=false
        viewModelScope.launch {
            noteDatabase.getNoteDao().deleteNote(note)
            isDeleted.value=true
        }
    }

}