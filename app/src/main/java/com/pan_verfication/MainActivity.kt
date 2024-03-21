package com.pan_verfication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.pan_varification.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        edit_b_date.doAfterTextChanged {
            validateInputs()
        }

        edit_b_month.doAfterTextChanged {
            validateInputs()
        }

        edit_b_year.doAfterTextChanged {
            validateInputs()
        }

        editTextPAN.doAfterTextChanged {
            validateInputs()
        }

        buttonNext.setOnClickListener {
            showToast("Details submitted successfully")
            finish()
        }

        buttonNoPAN.setOnClickListener {
            finish()
        }

    }
    private fun validateInputs() {
        val day = edit_b_date.text.toString()
        val month = edit_b_month.text.toString()
        val year = edit_b_year.text.toString()
        val pan = editTextPAN.text.toString()

        val birthdate = "$day-$month-$year"
        Log.d("birthdate1= ", birthdate.toString());

         viewModel.validatePANAndBirthdate(pan, birthdate)

        viewModel.isNextButtonEnabled.observe(this) { isEnabled ->
            buttonNext.isEnabled = isEnabled
            Log.d("isEnabled= ", isEnabled.toString());

        }
        //buttonNext.isEnabled = isPANValid && isBirthdateValid
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}