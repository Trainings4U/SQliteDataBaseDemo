package com.trainings4u.studentdbdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter : BaseAdapter
{

    var context : Context? = null
    var studentList = ArrayList<Student>()


    constructor(mContext: Context,mStudentList : ArrayList<Student>)
    {
        this.context = mContext
        this.studentList = mStudentList
    }

    override fun getCount(): Int
    {
        return studentList.size
    }

    override fun getItem(position: Int): Any
    {
        return position
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {

        var student = studentList.get(position)

        var view = LayoutInflater.from(context).inflate(R.layout.student_row,parent,false)

        var tvName = view.findViewById<TextView>(R.id.tvName)
        var tvGender = view.findViewById<TextView>(R.id.tvGender)
        var tvDOB = view.findViewById<TextView>(R.id.tvDOB)
        var tvMobileNumber = view.findViewById<TextView>(R.id.tvNumber)

        tvName.setText(""+student.userName)
        tvGender.setText(""+student.userGender)
        tvMobileNumber.setText(""+student.userMobile)
        tvDOB.setText(""+student.userDOB)


        return view

    }

}