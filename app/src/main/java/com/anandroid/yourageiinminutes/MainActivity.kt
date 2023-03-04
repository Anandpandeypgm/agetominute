package com.anandroid.yourageiinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.anandroid.yourageiinminutes.R.id.btnDate
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

   private var tvAgeInMinutes :TextView? =null
    private var tvSelectedDate : TextView? =null

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAgeInMinutes =findViewById(R.id.tvAgeInMinutes)
        val btnDate : Button = findViewById(btnDate)
        tvSelectedDate= findViewById(R.id.tvselectedDate)
        btnDate.setOnClickListener {
         clickDatePicker()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year =myCalendar.get(Calendar.YEAR)
        val month =myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
val dpd =         DatePickerDialog(this,
    DatePickerDialog.OnDateSetListener{
            view,year,month,dayofMonth ->

        Toast.makeText(this,
            "Year was $year,month was${month+1},day of the month was $dayofMonth",Toast.LENGTH_LONG).show()

        val selectedDate ="$dayofMonth/${month+1}/$year"
        tvSelectedDate?.text = selectedDate
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val theDate = sdf.parse(selectedDate)

        val selectedDateInMinutes= theDate.time/60000
        val currntDate= sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateInMinutes=currntDate.time/60000
        val differenceInMinutes= currentDateInMinutes- selectedDateInMinutes
        tvAgeInMinutes?.text =differenceInMinutes.toString()

    },year,
    month,
    day
)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
dpd.show()


    }
}