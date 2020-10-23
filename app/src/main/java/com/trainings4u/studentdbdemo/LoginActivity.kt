package com.trainings4u.studentdbdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mani.sharedprefernece.RegisterActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var tvLogin: TextView
    lateinit var tvCancel: TextView
    lateinit var tvRegister: TextView

    lateinit var etUserMobileNumber: EditText
    lateinit var etPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUserMobileNumber = findViewById(R.id.etUserMobileNumber)
        etPassword = findViewById(R.id.etPassword)

        tvLogin = findViewById(R.id.tvLogin)
        tvCancel = findViewById(R.id.tvCancel)
        tvRegister = findViewById(R.id.tvRegister)

        tvLogin.setOnClickListener(this)
        tvCancel.setOnClickListener(this)
        tvRegister.setOnClickListener(this)

    }

    override fun onClick(v: View?) {


        when (v!!.id) {
            R.id.tvLogin -> {

                var userMobileNumber = etUserMobileNumber.text.toString()
                var password = etPassword.text.toString()

                val dbHelper : DBHelper = DBHelper(applicationContext)

                var value = dbHelper.checkLoginCrediantails(userMobileNumber,password)


                println("The Boolean Value is ${value}")

                if (value == true)
                {
                    intent = Intent(this,DashBoardActivity::class.java)
                    intent.putExtra("userMobileNumber",userMobileNumber)
                    startActivity(intent)
                }
            }

            R.id.tvCancel -> {

                if (!etUserMobileNumber.text.toString().isEmpty() && !etPassword.text.toString().isEmpty()) {
                    etUserMobileNumber.setText("")
                    etPassword.setText("")
                }

            }

            R.id.tvRegister -> {

                intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }

        }
    }
}