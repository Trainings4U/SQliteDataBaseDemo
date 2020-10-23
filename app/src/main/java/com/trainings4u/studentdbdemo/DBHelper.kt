package com.trainings4u.studentdbdemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context,DB_NAME,null, DB_VER)
{

    companion object
    {
        val DB_NAME = "StudenDB"
        val DB_VER = 1
        val TBL_NAME = "StudentTBL"

        /* Columns  */

        val COL_USER_NAME = "userName"
        val COL_USER_MOBILE = "userMobile"
        val COL_USER_GENDER = "userGender"
        val COL_USER_DOB = "userDOB"
        val COL_USER_PASSWORD = "userPassword"

    }

    override fun onCreate(db: SQLiteDatabase?)
    {
        db!!.execSQL("create table StudentTBL(userName Text,userMobile Text PRIMARY KEY,userGender Text,userDOB Text,userPassword Text)")
    }


    fun checkLoginCrediantails(userMobileNumber : String,password : String) : Boolean
    {
        var database = this.readableDatabase

        var cursor = database.rawQuery("select * from StudentTBL where userMobile=? AND userPassword=?", arrayOf(userMobileNumber,password))

        if (cursor!=null)
        {
            if (cursor.count >  0)
            {
                return true
            }
        }

        return false
    }

    fun insertStudentRecord(userName: String,userPassword : String ,userGender : String,userMobile : String ,userDOB : String) : Long
    {

        var database = this.writableDatabase
        var contentValues = ContentValues()

        contentValues.put(COL_USER_NAME,userName)
        contentValues.put(COL_USER_PASSWORD,userPassword)
        contentValues.put(COL_USER_MOBILE,userMobile)
        contentValues.put(COL_USER_GENDER,userGender)
        contentValues.put(COL_USER_DOB,userDOB)


        return database.insert(TBL_NAME,null,contentValues)

    }

    fun upatedStudentRecord(userMobile: String,userName: String,userPassword: String) : Int
    {
        var database = this.writableDatabase
        var contentValues = ContentValues()

        contentValues.put(COL_USER_NAME,userName)
        contentValues.put(COL_USER_PASSWORD,userPassword)

        return database.update(TBL_NAME,contentValues,"userMobile=?", arrayOf(userMobile))
    }


    fun deleteStudentRecord(userMobile: String) : Int
    {
        var database = this.writableDatabase
        return database.delete(TBL_NAME,"userMobile=?", arrayOf(userMobile))
    }


    fun readStudentDataList() : ArrayList<Student>
    {

        var studentList = ArrayList<Student>()

        var database = this.readableDatabase

        var cursor = database.rawQuery("select * from StudentTBL",null)

        if (cursor !=null)
        {
            if (cursor.moveToFirst())
            {
                do {

                    var userMobile  = cursor.getString(cursor.getColumnIndex(COL_USER_MOBILE))
                    var userName  = cursor.getString(cursor.getColumnIndex(COL_USER_NAME))
                    var userGender  = cursor.getString(cursor.getColumnIndex(COL_USER_GENDER))
                    var userDOB  = cursor.getString(cursor.getColumnIndex(COL_USER_DOB))

                    var student = Student(userMobile = userMobile,userName = userName,userDOB = userDOB,userGender = userGender)
                    studentList.add(student)


                }while (cursor.moveToNext())
            }
        }
        return studentList
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

}