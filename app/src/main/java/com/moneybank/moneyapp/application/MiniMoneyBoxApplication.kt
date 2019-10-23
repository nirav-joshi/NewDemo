package com.moneybank.moneyapp.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatDelegate
import com.moneybank.moneyapp.util.getAccessToken
import com.moneybank.moneyapp.util.getDefaultPreference


/**
 * [MiniMoneyBoxApplication] :
 *
 * @author : Nirav Joshi
 * @version 1.0.0
 * @since 10/15/2019
 */
class MiniMoneyBoxApplication : Application() {
    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        @Volatile
        @JvmStatic
        lateinit var context: MiniMoneyBoxApplication

        @JvmStatic
        fun isNetworkConnected(): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            val activeNetwork = connectivityManager?.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }
    }
    fun getAccessToken() = context.getDefaultPreference().getAccessToken()
    override fun onCreate() {
        super.onCreate()
        context = this
    }


}