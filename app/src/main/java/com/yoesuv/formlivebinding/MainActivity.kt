package com.yoesuv.formlivebinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        applyToolbarWindowInsets()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.main = viewModel
        viewModel.setupLiveData(this, this)
    }

    private fun applyToolbarWindowInsets() {
        val initialPaddingTop = binding.toolbar.paddingTop

        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { toolbar, insets ->
            val topInset =
                insets
                    .getInsets(
                        WindowInsetsCompat.Type.systemBars() or
                            WindowInsetsCompat.Type.displayCutout(),
                    ).top
            toolbar.setPadding(
                toolbar.paddingLeft,
                initialPaddingTop + topInset,
                toolbar.paddingRight,
                toolbar.paddingBottom,
            )
            insets
        }
        ViewCompat.requestApplyInsets(binding.toolbar)
    }
}
