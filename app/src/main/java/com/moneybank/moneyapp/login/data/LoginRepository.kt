package com.moneybank.moneyapp.login.data

import com.example.minimoneybox.login.model.LoginRequestDto
import com.example.minimoneybox.login.model.LoginResponseDto
import com.moneybank.moneyapp.api.RetrofitSingleton
import com.moneybank.moneyapp.api.enqueueOn
import com.moneybank.moneyapp.api.failure
import com.moneybank.moneyapp.api.success
import com.moneybank.moneyapp.application.MiniMoneyBoxApplication


/**
 * [LoginRepository] :
 *
 * Repository class used to provide connection to API end-points or Database for login functionality
 *
 * @author : Nirav Joshi
 * @version 1.0.0
 * @since 9/25/2019
 */
class LoginRepository private constructor() {
    object HOLDER {
        val instance = LoginRepository()
    }

    companion object {
        @JvmStatic
        fun getInstance() = HOLDER.instance
    }


    private val mApiService by lazy {
        return@lazy RetrofitSingleton.getInstance().provideApiService()
    }

    fun doLogin(requestDto: LoginRequestDto, onLoginCallback: (LoginResponseDto?) -> Unit) {
        if (MiniMoneyBoxApplication.isNetworkConnected()) {
            mApiService.login(requestDto).enqueueOn().success { _, response ->
                when {
                    response.isSuccessful && response.code() == 200 -> {
                        onLoginCallback(response.body())
                    }
                    else -> {
                        onLoginCallback(response.body())
                    }
                }
            } failure { _, t ->
                onLoginCallback(null)
            }
        }
    }
}