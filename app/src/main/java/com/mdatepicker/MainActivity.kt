package com.mdatepicker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.conjoinixPicker.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    var myDate : Date? = null
    var miniDate : Date? = null
    var maxDate : Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        d.setOnClickListener {

            ConjoinxDate.Builder()
                .title("Choose date of birth")
                .maxDate(maxDate)
                .dateTime(myDate)
                .minDate(miniDate)
                .pickerType(ConjoinxDate.Picker.DATE)
                .build(this){



                    myDate = it!!.toDate(yyyy_MM_DD)
                    d.text = it
                }


        }


        t.setOnClickListener {

            ConjoinxDate.Builder()
                .maxDate(Date())
                .dateTime(myDate)
                .pickerType(ConjoinxDate.Picker.TIME)
                .build(this){
                    t.text = it
                    myDate = it!!.toDate(HH_mm_ss)
                    Log.e("Deepak Time"," $it ")

            }

        }


        b.setOnClickListener {
            ConjoinxDate.Builder() .maxDate(Date())
                .dateTime(myDate)    .build(this){
                    myDate = it!!.toDate()
                    Log.e("Deepak both "," $it ")
                    b.text = it

                }
        }
    }





/*
    fun Activity.XSDateTime(value:Int=0, func: (month:String) -> Unit){

        val builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.activity_datetimee, null, false)

        builder.setView(view)

        val datePiker: DatePicker = view.findViewById(R.id.datepicker)
        val timePiker: TimePicker = view.findViewById(R.id.timepicker)

        val ok: TextView = view.findViewById(R.id.ok)
        val cancel: TextView = view.findViewById(R.id.cancel)

        val rd: RelativeLayout = view.findViewById(R.id.rd)
        val rt: RelativeLayout = view.findViewById(R.id.rt)


        val dialog = builder.create()


        when (value) {
            1 -> rt.visibility=View.GONE
            2 -> rd.visibility = View.GONE
            else -> {

            }
        }


        ok.setOnClickListener {

            val serverdate = "${datePiker.year}-${datePiker.month+1}-${datePiker.dayOfMonth}"


            val servertime ="${timePiker.currentHour}:${timePiker.currentMinute}:${"00"}"


            val both:String = serverdate +" "+ servertime


            when (value) {
                XSTimer.DATE -> func(serverdate)
                XSTimer.TIME -> func(servertime)
                else -> {
                    func(both)
                }
            }


            dialog.dismiss()
        }


        cancel.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }*/


}
