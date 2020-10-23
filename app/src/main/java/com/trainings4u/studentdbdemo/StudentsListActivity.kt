package com.trainings4u.studentdbdemo

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

class StudentsListActivity :Activity(), AdapterView.OnItemClickListener {
    lateinit var lvStudents : ListView

    lateinit var studentList : ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)
        lvStudents = findViewById(R.id.lvStudents)

        getData()

        lvStudents.setOnItemClickListener(this)


    }

    private fun getData() {

        val dbHelper : DBHelper = DBHelper(applicationContext)
        studentList = dbHelper.readStudentDataList()
        var studentAdapter = StudentAdapter(this,studentList)
        lvStudents.adapter = studentAdapter

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


        var userMobileNumber = studentList[position].userMobile

        var alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle(""+userMobileNumber)
        alertDialog.setMessage("DO You Want to Delete this ${userMobileNumber}")

        alertDialog.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int ->

            val dbHelper : DBHelper = DBHelper(applicationContext)
            dbHelper.deleteStudentRecord(userMobileNumber)
            dialogInterface.dismiss()

            studentList.clear()

            getData()



        })

        alertDialog.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->


            dialogInterface.dismiss()

        })


        alertDialog.show()
    }

}
