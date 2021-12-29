package com.example.instagram_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var logout: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        connectView()

        val i = intent.getStringExtra("email")
        val sqLiteDatabase = DataBaseHlpr(applicationContext)
        val data = sqLiteDatabase.getDetails(i.toString())

        if (data.isNotEmpty()) {
            name.text = "Name: ${data[0]}"
            email.text = "Email: ${data[1]}"
        }

        logout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    fun connectView() {
        name = findViewById(R.id.details_name)
        email = findViewById(R.id.details_email)
        logout = findViewById(R.id.btnlogout)
    }

}