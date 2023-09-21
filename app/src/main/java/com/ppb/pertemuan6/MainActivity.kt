package com.ppb.pertemuan6

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ppb.pertemuan6.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    val TAG : String = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provinsi = arrayOf(
            "Jawa Timur",
            "Jawa Tengah",
            "Jawa Barat",
            "DKI Jakarta",
            "DI Yogyakarta"
        )

        with(binding) {
            val adapterProvinsi = ArrayAdapter<String> (
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                provinsi
            )
            spinnerProvinsi.adapter = adapterProvinsi

            val countries = resources.getStringArray(R.array.countries)
            spinnerCountries.adapter = ArrayAdapter<String> (
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                countries
            )

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) {
                _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                Toast.makeText(
                    this@MainActivity,
                    selectedDate, Toast.LENGTH_SHORT
                ).show()
            }

            timePicker.setOnTimeChangedListener {
                view, hourOfDay, minute ->
                val selectedTime = "$hourOfDay:$minute"
                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT)
            }

            spinnerCountries.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        Toast.makeText(
                            this@MainActivity,
                            countries[position], Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }


            button.setOnClickListener {
                val countriesValue : String = spinnerCountries.selectedItem.toString()
                Log.d(TAG, "Countries : $countriesValue")

                val provinceValue : String = spinnerProvinsi.selectedItem.toString()
                Log.d(TAG, "Countries : $provinceValue")
            }

            btnShowCalendar.setOnClickListener {
                val datePicker = DatePicker()
                datePicker.show(
                    supportFragmentManager, "datePicker"
                )
            }

            btnShowTimePicker.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(
                    supportFragmentManager, "timePicker"
                )
            }
        }
    }

    override fun onDateSet(
        view: android.widget.DatePicker?,
        p1: Int,
        p2: Int,
        p3: Int
    ) {
        val selectedDate = "$p3/${p2 + 1}/$p1"
        Toast.makeText(
            this@MainActivity,
            selectedDate, Toast.LENGTH_SHORT
        )
    }


}
