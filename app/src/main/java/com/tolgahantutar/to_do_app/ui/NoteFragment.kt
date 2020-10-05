package com.tolgahantutar.to_do_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.tolgahantutar.to_do_app.R
import org.w3c.dom.Text

class NoteFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val relativeLayout = view?.findViewById<RelativeLayout>(R.id.relativeLayout)
        val linearLayout  = view?.findViewById<LinearLayout>(R.id.linearLayout)

        var textViewTitle = TextView(requireContext())
        val textViewTitleParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textViewTitleParams.setMargins(0,0,0,10)
        textViewTitle.layoutParams = textViewTitleParams

        val textViewDuration = TextView(requireContext())

        val textViewDurationParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textViewDurationParams.setMargins(0,0,0,5)
        textViewDurationParams.addRule(RelativeLayout.BELOW,textViewDuration.id)
        textViewDuration.layoutParams = textViewDurationParams

        val textViewPrice = TextView(requireContext())

        val textViewPriceParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textViewDurationParams.setMargins(0,0,0,5)
        textViewDurationParams.addRule(RelativeLayout.BELOW,textViewDuration.id)
        textViewPrice.layoutParams = textViewPriceParams

        val textViewNote = TextView(requireContext())

        val textViewNoteParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textViewNoteParams.addRule(RelativeLayout.BELOW,textViewPrice.id)
        textViewNote.layoutParams = textViewNoteParams

        relativeLayout?.addView(textViewTitle)
        relativeLayout?.addView(textViewDuration)
        relativeLayout?.addView(textViewPrice)
        relativeLayout?.addView(textViewNote)

        val cardView = CardView(requireContext())
        val cardViewParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        cardViewParams.setMargins(4,4,4,4)
        cardView.layoutParams = cardViewParams

        cardView.addView(relativeLayout)
        linearLayout?.addView(cardView)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

}