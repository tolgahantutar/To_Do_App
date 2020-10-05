package com.tolgahantutar.to_do_app.ui.addnote

import android.os.Bundle
import android.text.BoringLayout
import android.text.InputType
import android.view.*
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tolgahantutar.to_do_app.R
import com.tolgahantutar.to_do_app.db.Note
import com.tolgahantutar.to_do_app.ui.addnote.AddNoteFragmentArgs
import com.tolgahantutar.to_do_app.ui.addnote.AddNoteFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Double.isNaN
import java.lang.Double.parseDouble
import java.lang.NumberFormatException

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
 var note : Note ? = null
    companion object {
        fun newInstance() =
            AddNoteFragment()
    }

   private val viewModel: AddNoteFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.add_note_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Creating relative layout
        val relativeLayout = view?.findViewById<RelativeLayout>(R.id.relative_layout_add_note)




        //Title Edit Text
        val editTextTitle = EditText(requireView().context)
        val editTextTitleParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)
        editTextTitle.layoutParams=editTextTitleParams
        editTextTitle.hint = "Title"

        //Duration Edit Text
        val editTextDuration = EditText(requireView().context)
        val editTextDurationParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)
        editTextDurationParams.addRule(RelativeLayout.BELOW,editTextTitle.id)
        editTextDurationParams.setMargins(0,70,0,0)
        editTextDuration.layoutParams = editTextDurationParams
        editTextDuration.hint = "Duration"


        //Price Edit Text
        val editTextPrice = EditText(requireView().context)
        val editTextPriceParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)
        editTextPriceParams.setMargins(0,140,0,0)
        editTextPrice.layoutParams = editTextPriceParams
        editTextPrice.hint = "Price"


        //Note Edit Text
        val editTextNote = EditText(requireView().context)
        val editTextNoteParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)
        editTextNoteParams.setMargins(0,210,0,0)
        editTextNote.inputType=InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE
        editTextNote.gravity = Gravity.TOP
        editTextNote.layoutParams = editTextNoteParams
        editTextNote.hint = "Note"

        //Save Button
        val buttonSave = FloatingActionButton(requireView().context)
        val saveButtonParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)
        saveButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
        saveButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        saveButtonParams.setMargins(0,0,20,20)
        buttonSave.layoutParams = saveButtonParams
        buttonSave.setImageDrawable(resources.getDrawable(R.drawable.ic_done))


        //Add all elements to the UI
        relativeLayout?.addView(buttonSave)
        relativeLayout?.addView(editTextTitle)
        relativeLayout?.addView(editTextDuration)
        relativeLayout?.addView(editTextPrice)
        relativeLayout?.addView(editTextNote)
    viewModel.isNoteSaved.observe(viewLifecycleOwner, Observer {
if (it){
    Toast.makeText(context, "Note Saved", Toast.LENGTH_LONG).show()
    val action =
        AddNoteFragmentDirections.actionSave()
    Navigation.findNavController(requireView()).navigate(action)
}
    })
        viewModel.isNoteUpdated.observe(viewLifecycleOwner, Observer {
            if (it){
                Toast.makeText(context, "Note Updated", Toast.LENGTH_LONG).show()
                val action =
                    AddNoteFragmentDirections.actionSave()
                Navigation.findNavController(requireView()).navigate(action)
            }
        })
        viewModel.isDeleted.observe(viewLifecycleOwner, Observer {
            if (it){
                Toast.makeText(context, "Note Deleted", Toast.LENGTH_LONG).show()
                val action =
                    AddNoteFragmentDirections.actionSave()
                Navigation.findNavController(requireView()).navigate(action)
            }
        })
        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(
                it
            ).note
            editTextTitle.setText(note?.title)
            editTextDuration.setText(note?.duration)
            if(note?.price.toString()=="null"){
                editTextPrice.hint="Price"
            }
            else {
                editTextPrice.setText(note?.price.toString())
            }
            editTextNote.setText(note?.note)
        }
        val noteCopy = note
        buttonSave.setOnClickListener {

            val title: String = editTextTitle.text.toString().trim()
            val duration: String = editTextDuration.text.toString().trim()
            if(editTextPrice.text.toString().trim().isEmpty()){
                editTextPrice.error = "price required"
                return@setOnClickListener
            }
            fun isDouble (string: String) : Boolean{
                try {
                    parseDouble(string)
                }catch (e : NumberFormatException){
                    return false
                }
                return true
            }
            if(!(isDouble(editTextPrice.text.toString().trim()))){
                editTextPrice.error = "price must be double!"
                return@setOnClickListener
            }
            val price: Double = parseDouble(editTextPrice.text.toString().trim())
            val note: String = editTextNote.text.toString().trim()
            if (title.isEmpty()){
                editTextTitle.error = "title required"
                return@setOnClickListener
            }
            if(duration.isEmpty()){
                editTextDuration.error= "duration required"
                return@setOnClickListener
            }
            if(editTextPrice.text.toString().trim().isNullOrEmpty()){
                editTextPrice.error = "price required"
                return@setOnClickListener
            }
            if(note.isEmpty()){
                editTextNote.error = "note required"
                return@setOnClickListener
            }
            viewModel.saveNote(noteCopy,title,duration,price,note)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete ->
                if(note!= null) {
                    AlertDialog.Builder(requireContext()).apply {
                        setTitle("Are You Sure ? ")
                        setMessage("You cannot undo this operation")
                        setPositiveButton("Yes"){_, _ ->
                            viewModel.deleteNote(note)
                        }
                        setNegativeButton("No"){_, _ ->}
                    }.create().show()

            } else Toast.makeText(
                requireContext(),
                "Cannot Delete",
                Toast.LENGTH_SHORT
            ).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu , menu)
    }

}