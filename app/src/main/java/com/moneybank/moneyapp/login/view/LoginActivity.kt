package com.moneybank.moneyapp.login.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.login.model.LoginRequestDto
import com.moneybank.moneyapp.R
import com.moneybank.moneyapp.base.AbstractBaseActivity
import com.moneybank.moneyapp.base.absActivityBuilder

class LoginActivity : AbstractBaseActivity() {

    private val loginActivityBinder by lazy {
        return@lazy LoginActivityBinder()
    }

    private val loginViewModel by lazy {
        ViewModelProviders.of(this)[LoginViewModel::class.java]
    }


    override fun setUpBuilder() = absActivityBuilder {
        contentView = R.layout.activity_login
        abstractBinding = loginActivityBinder
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        observeLoginData()
        loginActivityBinder.binding?.btnSignIn?.setOnClickListener {
             if(loginActivityBinder.isInputValid()){
                 loginViewModel.doLogin(loginActivityBinder.getRequestObject())
             }
        }

    }

    private fun observeLoginData() {
        loginViewModel.loginLiveData.observe(this, Observer {

        })
    }
}
