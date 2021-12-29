package com.example.instagram_login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    lateinit var sinIn: Button
    lateinit var logIn: Button

    lateinit var spf: SharedPreferences
    lateinit var editr: SharedPreferences.Editor

    private val database by lazy { DataBaseHlpr(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        connectView()

        spf = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        editr = spf.edit()

        sinIn.setOnClickListener {
            val e = email.text.toString()

            if (password.text.toString() == confirmPassword.text.toString()) {
                val check = database.logIn(email.text.toString(), password.text.toString())
                if (check != "Account is not exist")
                    Toast.makeText(this, "Account is already exist.", Toast.LENGTH_LONG).show()
                else {
                    val res = database.signIN(
                        name.text.toString(),
                        email.text.toString(),
                        password.text.toString()
                    )
                    if (res > 0) {
                        Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "Password and Confirm Password must be match.",
                    Toast.LENGTH_LONG
                ).show()
            }

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("email", e)
            startActivity(intent)

        }

        logIn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    private fun connectView() {
        name = findViewById(R.id.sign_in_et_name)
        email = findViewById(R.id.sign_in_et_email)
        password = findViewById(R.id.sign_in_et_password)
        confirmPassword = findViewById(R.id.sign_in_et_confirm_password)
        sinIn = findViewById(R.id.sign_in_btn_sign_in)
        logIn = findViewById(R.id.btnSign)
    }


}