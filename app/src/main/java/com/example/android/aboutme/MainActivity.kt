/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.aboutme.databinding.ActivityMainBinding


/**
 * Main Activity of the AboutMe app. This app demonstrates:
 *     * LinearLayout with TextViews, ImageView, Button, EditText, and ScrollView
 *     * ScrollView to display scrollable text
 *     * Getting user input with an EditText.
 *     * Click handler for a Button to retrieve text from an EditText and set it in a TextView.
 *     * Setting the visibility status of a view.
 *     * Data binding between MainActivity and activity_main.xml. How to remove findViewById,
 *       and how to display data in views using the data binding object.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName = MyName("Pratyay Roy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        //for databinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //getting the myName variable from the layout file
        binding.myName = myName


//        findViewById<Button>(R.id.done_btn).setOnClickListener {
//            addNickname(it) //passes the button as a view
//        }

        binding.doneBtn.setOnClickListener {
            addNickname(it)
        }
    }

    /**
     * view -> Done button
     */
    private fun addNickname(view: View) {
//        val editText = findViewById<EditText>(R.id.nickname_edit)
//        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        binding.apply { // for restructuring??
//            binding.nicknameText.text = binding.nicknameEdit.text

            //using binding variable from layout & data class
            myName?.nickName = binding.nicknameEdit.text.toString()

            invalidateAll() // to re-init the binding with new data
            binding.nicknameEdit.visibility = View.GONE
            view.visibility = View.GONE
            binding.nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}