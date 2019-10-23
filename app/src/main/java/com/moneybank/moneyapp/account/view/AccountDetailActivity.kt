package com.moneybank.moneyapp.account.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.moneybank.moneyapp.R
import com.moneybank.moneyapp.account.view.AccountDetailActivityBinder
import com.moneybank.moneyapp.base.AbstractBaseActivity
import com.moneybank.moneyapp.base.absActivityBuilder


/**
 * [AccountDetailActivity] :
 *
 *
 * @author Jeel Vankhede
 * @version 1.0.0
 * @since 23-10-2019
 */
class AccountDetailActivity : AbstractBaseActivity() {

    private val accountDetailActivityBinder by lazy {
        return@lazy AccountDetailActivityBinder()
    }

    private val accountViewModel by lazy {
        ViewModelProviders.of(this)[AccountViewModel::class.java]
    }

    override fun setUpBuilder() = absActivityBuilder {
        contentView = R.layout.activity_account_detail
        abstractBinding = accountDetailActivityBinder
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.title_account_detail)
        observePayment()
        setupPaymentClick()
    }

    private fun observePayment() {
        accountViewModel.paymentLiveData.observe(this, Observer {

        })
    }

    private fun setupPaymentClick() {
        accountDetailActivityBinder.binding?.btnAdd?.setOnClickListener {
            accountViewModel.oneOffPayment(10,10)
        }
    }
}