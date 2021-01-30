package br.com.petapp.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class GenderAdapter(context: Context, textViewResourceId: Int, private val values: List<String>)
    : ArrayAdapter<String>(context, textViewResourceId, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return setTextView(super.getView(position, convertView, parent) as TextView, position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return setTextView(super.getDropDownView(position, convertView, parent) as TextView, position)
    }

    override fun isEnabled(position: Int) = position != 0

    private fun setTextView(textViewGender: TextView, position: Int): TextView {
        textViewGender.text = values[position]
        if(isEnabled(position)) textViewGender.setTextColor(Color.BLACK) else textViewGender.setTextColor(Color.GRAY)
        return textViewGender
    }
}