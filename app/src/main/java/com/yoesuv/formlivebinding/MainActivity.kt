 package com.yoesuv.formlivebinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.formlivebinding.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
     private lateinit var viewModel: MainViewModel

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
         binding.lifecycleOwner = this

         setSupportActionBar(binding.toolbar)

         viewModel = ViewModelProvider(this)[MainViewModel::class.java]
         binding.main = viewModel
         viewModel.setupLiveData(this, this)
     }
}
