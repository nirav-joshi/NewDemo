package com.moneybank.moneyapp.account.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimoneybox.login.model.LoginRequestDto
import com.example.minimoneybox.login.model.LoginResponseDto
import com.moneybank.moneyapp.account.data.AccountRepository
import com.moneybank.moneyapp.account.model.AccountDetailDTO
import com.moneybank.moneyapp.login.data.LoginRepository


/**
 * [AccountViewModel] :
 *
 *
 * @author Jeel Vankhede
 * @version 1.0.0
 * @since 23-10-2019
 */
class AccountViewModel : ViewModel() {

    private val accountRepository by lazy {
        return@lazy AccountRepository.instance
    }

    val accountLiveData: LiveData<AccountDetailDTO?> = MutableLiveData()
    val paymentLiveData: LiveData<Any?> = MutableLiveData()

    fun getAccountDetail(){
        accountRepository.getAccountDetails {
            (accountLiveData as? MutableLiveData<*>)?.value=it
        }

    }

    fun oneOffPayment(amount:Long,investorProductId:Int){
        accountRepository.oneoffPayment(amount,investorProductId) {
            (paymentLiveData as? MutableLiveData<*>)?.value=it
        }

    }
}