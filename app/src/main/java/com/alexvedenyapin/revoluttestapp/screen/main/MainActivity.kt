package com.alexvedenyapin.revoluttestapp.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.alexvedenyapin.revoluttestapp.R
import com.alexvedenyapin.revoluttestapp.screen.main.di.MainComponentProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val component by lazy {
        (application as MainComponentProvider).provideMainComponent(this)
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)

        viewModel.doOnCreate()
    }
}