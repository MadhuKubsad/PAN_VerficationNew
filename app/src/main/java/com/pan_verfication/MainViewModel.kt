package com.pan_varification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class MainViewModel : ViewModel() {

    private val _isNextButtonEnabled = MutableLiveData<Boolean>()
    val isNextButtonEnabled: LiveData<Boolean> = _isNextButtonEnabled

    init {
        _isNextButtonEnabled.value = false
    }

    fun validatePANAndBirthdate(pan: String, birthdate: String) {
        val isPANValid = isPANValid(pan)
        val isBirthdateValid = isBirthdateValid(birthdate)
        Log.d("isPANValid= ", isPANValid.toString())
        Log.d("birthdate= ", isBirthdateValid.toString())

        _isNextButtonEnabled.value = isPANValid && isBirthdateValid
    }

    fun isPANValid(pan: String): Boolean {
        // Validate PAN on the client-side
        return pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}".toRegex())
    }

    fun isBirthdateValid(birthdate: String): Boolean {
        // Validate birthdate on the client-side
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())


        dateFormat.isLenient = false
        return try {
            dateFormat.parse(birthdate)
            Log.d("dateFormat= ", dateFormat.parse(birthdate).toString());

            true
        } catch (e: Exception) {
            false
        }
    }
}
