package com.moneybank.moneyapp.login.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minimoneybox.login.model.LoginRequestDto
import com.example.minimoneybox.login.model.LoginResponseDto
import com.moneybank.moneyapp.login.data.LoginRepository
import androidx.lifecycle.LiveData as LiveData


/**
 * [LoginViewModel] :
 *
 * @author : Nirav Joshi
 * @version 1.0.0
 * @since 7/11/2019
 */
class LoginViewModel : ViewModel() {
    private val loginRepository by lazy {
        return@lazy LoginRepository.getInstance()
    }

    val loginLiveData: LiveData<LoginResponseDto?> = MutableLiveData()



    fun doLogin(requestDto: LoginRequestDto){
        loginRepository.doLogin(requestDto){
            (loginLiveData as? MutableLiveData<*>)?.value=it
        }

    }



}