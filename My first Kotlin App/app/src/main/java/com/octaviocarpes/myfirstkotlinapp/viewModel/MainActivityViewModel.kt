package com.octaviocarpes.myfirstkotlinapp.viewModel

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast

class MainActivityViewModel: ViewModel() {

    lateinit var textView: TextView

    fun toastMe(context: AppCompatActivity) {
        val myToast = Toast.makeText(context, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun countMe(context: AppCompatActivity) {
        var counter: Int = getTextViewData()
        counter = counter?.inc()
        textView.text = counter.toString()
    }

    fun getTextViewData(): Int {
        return Integer.parseInt(textView.text.toString())
    }
}