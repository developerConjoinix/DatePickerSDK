package com.conjoinixPicker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import java.util.*

/**
 * Created by deepakkanyan on 2019-09-03 , 14:45.
 */
class ConjoinxDate{


    object Picker {
        const val TIME = 100
        const val DATE = 200
        const val DATE_TIME = 300
    }
    @Suppress("DEPRECATION")
    data class Builder(
        var title: String? = null,
        var dateTime: Date? = null,
        var maxDate: Date? = null,
        var minDate: Date? = null,

        var pickerType : Int = Picker.DATE_TIME) {

        fun title(title: String?) = apply { this.title = title }
        fun dateTime(dateTime: Date?) = apply { this.dateTime = dateTime }
        fun maxDate(maxDate: Date?) = apply { this.maxDate = maxDate }
        fun minDate(minDate: Date?) = apply { this.minDate = minDate }
        fun pickerType(pickerType: Int) = apply { this.pickerType = pickerType }
        fun build(context: Context , onClick : (date : String?) -> Unit ) =  buildPicker(context, onClick)

        @SuppressLint("InflateParams")
        private fun buildPicker(context: Context,onClick : (date : String?) -> Unit )
        {

            val builder = AlertDialog.Builder(context)
            val view = LayoutInflater.from(context). inflate(R.layout.activity_datetime,
                    null, false)
            builder.setView(view)
            val datePiker: DatePicker = view.findViewById(R.id.datePicker)
            val timePiker: TimePicker = view.findViewById(R.id.timePicker)
            val btnOk: TextView = view.findViewById(R.id.ok)
            val btnCancel: TextView = view.findViewById(R.id.btnCancel)
            val pickerTitle: TextView = view.findViewById(R.id.pickerTitle)

            if(title!=null)
            {
                pickerTitle.visibilityShow()
                pickerTitle.text = title
            }

            val alertDialog = builder.create()
            val cal = Calendar.getInstance()
            cal.timeInMillis = if(dateTime != null) dateTime!!.time else {
                nowDateTime().toDate().time}

            if(minDate != null){
                datePiker.minDate = minDate!!.time
            }
            if(maxDate != null){
                datePiker.maxDate = maxDate!!.time
            }


            val  day = cal.get(Calendar.DATE)
            val  month = cal.get(Calendar.MONTH)
            val  year = cal.get(Calendar.YEAR)
            val  hour = cal.get(Calendar.HOUR_OF_DAY)
            val   min = cal.get(Calendar.MINUTE)

            when(pickerType) {

              Picker.TIME -> {
                  datePiker.visibilityGone()

                  if(Build.VERSION.SDK_INT >= 23) {
                      timePiker.hour = hour
                      timePiker.minute = min
                  }else{
                      timePiker.currentHour = hour
                      timePiker.currentMinute = min
                  }


              }

              Picker.DATE -> {
                   timePiker.visibilityGone()
                   datePiker.updateDate(year, month+1,day)

              }

              Picker.DATE_TIME -> {



                  if(Build.VERSION.SDK_INT >= 23) {
                      timePiker.hour = hour
                      timePiker.minute = min
                  }else{
                      timePiker.currentHour = hour
                      timePiker.currentMinute = min
                  }
                  datePiker.updateDate(year, month+1,day)

              }


          }

            btnCancel.setOnClickListener { alertDialog.dismiss() }
            btnOk.setOnClickListener {

               when(pickerType){


                  Picker.DATE -> {
                      onClick(datePiker.getDate())
                  }

                   Picker.TIME -> {
                       onClick(timePiker.getTime())
                   }

                   Picker.DATE_TIME -> {
                       onClick("${datePiker.getDate()} ${timePiker.getTime()}")
                   }


               }
                alertDialog.dismiss()


            }

            alertDialog.show()

        }
    }








}



fun DatePicker.getDate(): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month+1, dayOfMonth)
    return calendar.time.toStringDate(yyyy_MM_DD)
}


fun TimePicker.getTime(): String {

    return if(Build.VERSION.SDK_INT >= 23) {
        "${hour.makeCorrect()}:${minute.makeCorrect()}:00"

    }else{
        "${currentHour.makeCorrect()}:${currentMinute.makeCorrect()}:00"

    }
}

fun Int.makeCorrect() : String {

    if(this > 9)
    {
        return "$this"
    }

    return "0$this"
}

fun View.visibilityGone(){
    visibility =  View.GONE
}

fun View.visibilityShow(){
    visibility =  View.VISIBLE
}