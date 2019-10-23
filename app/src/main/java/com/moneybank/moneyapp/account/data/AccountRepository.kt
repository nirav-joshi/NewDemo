package com.moneybank.moneyapp.account.data

import com.example.minimoneybox.login.model.LoginRequestDto
import com.example.minimoneybox.login.model.LoginResponseDto
import com.moneybank.moneyapp.account.model.AccountDetailDTO
import com.moneybank.moneyapp.api.RetrofitSingleton
import com.moneybank.moneyapp.api.enqueueOn
import com.moneybank.moneyapp.api.failure
import com.moneybank.moneyapp.api.success
import com.moneybank.moneyapp.application.MiniMoneyBoxApplication


/**
 * [AccountRepository] :
 *
 *
 * @author Jeel Vankhede
 * @version 1.0.0
 * @since 23-10-2019
 */
class AccountRepository private constructor() {
    object HOLDER {
        val obj = AccountRepository()
    }

    companion object {
        @JvmStatic
        val instance: AccountRepository = HOLDER.obj
    }

    private val mApiService by lazy {
        return@lazy RetrofitSingleton.getInstance().provideApiService()
    }

    fun getAccountDetails( onAccountCallback: (AccountDetailDTO?) -> Unit) {
        if (MiniMoneyBoxApplication.isNetworkConnected()) {
            mApiService.getAccountDetail().enqueueOn().success { _, response ->
                when {
                    response.isSuccessful && response.code() == 200 -> {
                        onAccountCallback(response.body())
                    }
                    else -> {
                        onAccountCallback(response.body())
                    }
                }
            } failure { _, t ->
                onAccountCallback(null)
            }
        }
    }
}