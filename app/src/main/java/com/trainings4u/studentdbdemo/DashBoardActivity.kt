package com.trainings4u.studentdbdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*

class DashBoardActivity : Activity(), View.OnClickListener {

    lateinit var etUserName : EditText
    lateinit var etPassword : EditText
    lateinit var etDate : EditText
    lateinit var etUserMobileNumber : EditText
    lateinit var spGender : EditText
    lateinit var tvUpdate : TextView
    lateinit var tvCancel : TextView
    lateinit var tvStudentsList : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        var userMobileNumber = intent.extras!!.getString("userMobileNumber")

        etUserName = findViewById(R.id.etUserName)
        etUserMobileNumber = findViewById(R.id.etUserMobileNumber)
        etDate = findViewById(R.id.etDate)
        etPassword = findViewById(R.id.etPassword)
        spGender        = findViewById(R.id.spGender)
        tvCancel     = findViewById(R.id.tvCancel)
        tvUpdate     = findViewById(R.id.tvUpdate)
        tvStudentsList = findViewById(R.id.tvStudentsList)
        tvCancel.setOnClickListener(this)
        tvUpdate.setOnClickListener(this)
        tvStudentsList.setOnClickListener(this)

        getDataFromDatabase(userMobileNumber)

    }

    private fun getDataFromDatabase(userMobileNumber: String?) {

        val dbHelper : DBHelper = DBHelper(applicationContext)

        var database  = dbHelper.readableDatabase

        var cursor  = database.rawQuery("select * from StudentTBL where userMobile=?", arrayOf(userMobileNumber))
        if (cursor!=null)
        {
            if (cursor.moveToFirst())
            {
             do {

                 var userName = cursor.getString(cursor.getColumnIndex(DBHelper.COL_USER_NAME))
                 var userPassword = cursor.getString(cursor.getColumnIndex(DBHelper.COL_USER_PASSWORD))
                 var userGender = cursor.getString(cursor.getColumnIndex(DBHelper.COL_USER_GENDER))
                 var userDOB = cursor.getString(cursor.getColumnIndex(DBHelper.COL_USER_DOB))

                 etUserMobileNumber.setText(""+userMobileNumber)
                 etUserName.setText(""+userName)
                 etPassword.setText(""+userPassword)
                 etDate.setText(""+userDOB)
                 spGender.setText(""+userGender)



             }while (cursor.moveToNext())
            }
        }


    }

    override fun onClick(v: View?) {

        when(v!!.id)
        {
            R.id.tvUpdate ->
            {

                var userMobileNumber  = etUserMobileNumber.text.toString()
                var userName          = etUserName.text.toString()
                var userPassword      = etPassword.text.toString()

                val dbHelper : DBHelper = DBHelper(applicationContext)

                var id = dbHelper.upatedStudentRecord(userMobileNumber,userName,userPassword)

                if (!id.equals("-1"))
                {
                    Toast.makeText(this,"Record is Updated Successfully",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this,"Record is Not Updated ",Toast.LENGTH_SHORT).show()
                }
            }

            R.id.tvCancel ->{}

            R.id.tvStudentsList ->{

                startActivity(Intent(this,StudentsListActivity::class.java))
            }
        }
    }

}
