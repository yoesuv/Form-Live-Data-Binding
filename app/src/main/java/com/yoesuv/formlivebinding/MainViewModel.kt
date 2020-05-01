package com.yoesuv.formlivebinding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var email: MutableLiveData<String> = MutableLiveData();
    var password: MutableLiveData<String> = MutableLiveData();

}