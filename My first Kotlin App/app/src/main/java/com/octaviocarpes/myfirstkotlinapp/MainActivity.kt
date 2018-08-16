package com.octaviocarpes.myfirstkotlinapp

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.octaviocarpes.myfirstkotlinapp.viewModel.MainActivityViewModel
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var toastButton: Button
    lateinit var countButton: Button
    lateinit var randomButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        setView(viewModel)
    }

    fun setView(viewModel: MainActivityViewModel) {
        setTextView(viewModel)
        setToastButton(viewModel)
        setCountButton(viewModel)
        setRandomButton(viewModel)
    }


    fun setTextView(viewModel: MainActivityViewModel) {
        textView = findViewById(R.id.textView)
        viewModel.textView = textView;
    }

    fun setToastButton(viewModel: MainActivityViewModel) {
        toastButton = findViewById(R.id.button_toast)
        toastButton.setOnClickListener {
            viewModel.toastMe(this)
        }
    }

    fun setCountButton(viewModel: MainActivityViewModel) {
        countButton = findViewById(R.id.button_count)
        countButton.setOnClickListener {
            viewModel.countMe(this)
        }
    }



    fun setRandomButton(viewModel: MainActivityViewModel) {
        randomButton = findViewById(R.id.button_random)
        randomButton.setOnClickListener {

        }
    }




}
