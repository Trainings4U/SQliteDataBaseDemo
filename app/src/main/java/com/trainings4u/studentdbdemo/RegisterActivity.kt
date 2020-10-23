package com.mani.sharedprefernece

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import com.trainings4u.studentdbdemo.DBHelper
import com.trainings4u.studentdbdemo.LoginActivity
import com.trainings4u.studentdbdemo.R
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : Activity(), View.OnClickListener {

    lateinit var etUserName : EditText
    lateinit var etPassword : EditText
    lateinit var etCPassword : EditText
    lateinit var etDate : EditText
    lateinit var etetUserMobileNumber : EditText
    lateinit var spGender : Spinner
    lateinit var ivDate : ImageView
    lateinit var tvLogin : TextView
    lateinit var tvCancel : TextView
    lateinit var tvRegister : TextView


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUserName = findViewById(R.id.etUserName)
        etetUserMobileNumber = findViewById(R.id.etUserMobileNumber)
        etDate = findViewById(R.id.etDate)
        ivDate = findViewById(R.id.ivDate)
        etPassword = findViewById(R.id.etPassword)
        etCPassword = findViewById(R.id.etCPassword)
        spGender        = findViewById(R.id.spGender)

        tvRegister = findViewById(R.id.tvRegister)
        tvCancel     = findViewById(R.id.tvCancel)
        tvLogin     = findViewById(R.id.tvLogin)

        tvCancel.setOnClickListener(this)
        tvRegister.setOnClickListener(this)
        tvLogin.setOnClickListener(this)
        ivDate.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v!!.id)
        {
            R.id.tvLogin ->{

                intent = Intent(this,LoginActivity :: class.java)
                startActivity(intent)
            }

            R.id.tvCancel ->{}

            R.id.ivDate ->{

                var calendar = Calendar.getInstance()

                var currentYear = calendar.get(Calendar.YEAR)
                var currentMonth = calendar.get(Calendar.MONTH)
                var currentDate = calendar.get(Calendar.DATE)

                var datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->


                    var selectedYear = year
                    var selectedMonth = month+1
                    var selectedDate = dayOfMonth

                    var myBirthDate = ""+selectedDate + " / "+selectedMonth + " / " + selectedYear

                    etDate.setText(""+myBirthDate)

                },currentYear,currentMonth,currentDate)

                datePickerDialog.show()

            }

            R.id.tvRegister ->{


                var userName = etUserName.text.toString()
                var userMobileNumber = etUserMobileNumber.text.toString()
                var userGender = spGender.selectedItem.toString()
                var userBDate = etDate.text.toString()
                var userPassword = etPassword.text.toString()
                var userCPassword = etCPassword.text.toString()

                if (userPassword.equals(userCPassword))
                {
                    val dbHelper : DBHelper = DBHelper(applicationContext)

                    var id = dbHelper.insertStudentRecord(userName,userPassword,userGender,userMobileNumber,userBDate)


                    if (!id.equals("-1"))
                    {
                        intent = Intent(this,LoginActivity :: class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this,"Already Mobile Number is Exist",Toast.LENGTH_SHORT).show()
                    }


                }
                else
                {
                    Toast.makeText(this,"Password and COnfirmPassword Doesnt Match",Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

}
