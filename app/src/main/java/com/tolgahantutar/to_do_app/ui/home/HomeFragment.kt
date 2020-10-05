package com.tolgahantutar.to_do_app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tolgahantutar.to_do_app.R
import com.tolgahantutar.to_do_app.ui.home.HomeFragmentDirections
import com.tolgahantutar.to_do_app.ui.NotesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }
    private val viewModel: HomeFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            //Layout definitons
            val relativeLayout = view?.findViewById<RelativeLayout>(R.id.relative_layout)

            //Button definition
            val button = FloatingActionButton(requireView().context)
            val params : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE)
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE)
            params.setMargins(0,0,20,20)
            button.layoutParams = params
            button.setImageDrawable(resources.getDrawable(R.drawable.ic_add))
            button.setOnClickListener{
                val action =
                    HomeFragmentDirections.actionAddNote()
                Navigation.findNavController(it).navigate(action)
            }
            relativeLayout?.addView(button)

            //Recyclerview definition
            val recyclerView = RecyclerView(requireView().context)
            val recyclerViewParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT)
            recyclerView.layoutParams=recyclerViewParams

            relativeLayout?.addView(recyclerView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        viewModel.getAllNotes()
        viewModel.notes.observe(viewLifecycleOwner , Observer {
            recyclerView.adapter =
                NotesAdapter(it)
        })

    }



}