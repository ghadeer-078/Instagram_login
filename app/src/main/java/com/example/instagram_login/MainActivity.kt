package com.example.instagram_login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var etPersonName: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnSign: Button

    lateinit var spf: SharedPreferences
    lateinit var editr: SharedPreferences.Editor

    private val database by lazy { DataBaseHlpr(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectView()

        spf = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        editr = spf.edit()


        btnLogin.setOnClickListener {
            val e = etPersonName.text.toString()
            val p = etPassword.text.toString()

            val res = database.logIn(e, p)

            if (res == "true") {
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("email", e)
                startActivity(intent)
            } else
                Toast.makeText(this, res, Toast.LENGTH_LONG).show()
        }


        btnSign.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


    }


    fun connectView() {
        etPersonName = findViewById(R.id.etPersonName)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSign = findViewById(R.id.btnSign)
    }
}