package com.example.sharedpreferencesdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.sharedpreferencesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var nameText:EditText
    private lateinit var ageText:EditText
    private lateinit var  sf:SharedPreferences
    private lateinit var  editor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nameText =binding.etName
        ageText =binding.etAge
        sf =getSharedPreferences("my_sf", MODE_PRIVATE)
        editor =sf.edit()

    }
    override fun onPause() {
        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        editor.apply {
            putString("sf_name",name)
            putInt("sf_age",age)
            //to save
            commit()
        }

    }
//to display saved data
    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name",null)
        val age = sf.getInt("sf_age",0)
        nameText.setText(name)
        if(age!=0) {
            ageText.setText(age.toString())
        }

    }
}