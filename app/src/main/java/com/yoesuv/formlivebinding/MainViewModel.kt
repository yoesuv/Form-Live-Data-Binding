package com.yoesuv.formlivebinding

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.yoesuv.utils.validateEmail
import com.yoesuv.utils.validatePassword

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    var errorEmail: MutableLiveData<String?> = MutableLiveData()
    var errorPassword: MutableLiveData<String?> = MutableLiveData()

    fun setupLiveData(lifecycleOwner: LifecycleOwner, context: Context) {
        email.observe(lifecycleOwner, Observer { email ->
            val validationModel = email.validateEmail(context)
            errorEmail.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, Observer { password ->
            val validationModel = password.validatePassword(context)
            errorPassword.postValue(validationModel.message)
        })
    }

}