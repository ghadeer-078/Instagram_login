package com.example.instagram_login

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHlpr(context: Context) : SQLiteOpenHelper(context, "reg.db", null, 1) {

    var sqLiteDatabase: SQLiteDatabase = writableDatabase


    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table USERS (Name text , EMAIL text, Password text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    fun logIn(email: String, password: String): String {
        val emailExist = sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE EMAIL = '$email'", null)
        if (emailExist.count > 0) {
            val res = sqLiteDatabase.rawQuery(
                "SELECT * FROM USERS WHERE EMAIL = '$email' AND PASSWORD = '$password'",
                null
            )
            if (res.count > 0)
                return "true"
            return "Password not correct"
        } else {
            return "Account is not exist"
        }

    }

    fun signIN(name: String, email: String, password: String): Long {
        val cv = ContentValues()
        cv.put("NAME", name)
        cv.put("EMAIL", email)
        cv.put("PASSWORD", password)
        return sqLiteDatabase.insert("USERS", null, cv)
    }

    fun getDetails(e: String): List<String> {
        val c = sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE EMAIL = '$e'", null)
        if (c.moveToFirst())
            return listOf(c.getString(0).toString(), c.getString(1).toString())
        return listOf(":(", ":(")
    }

}