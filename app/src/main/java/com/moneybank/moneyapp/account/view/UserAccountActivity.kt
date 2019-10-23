package com.example.minimoneybox.account.view

import android.os.Bundle
import com.moneybank.moneyapp.R
import com.moneybank.moneyapp.base.AbstractBaseActivity
import com.moneybank.moneyapp.base.absActivityBuilder


/**
 * [UserAccountActivity] :
 *
 *
 * @author Jeel Vankhede
 * @version 1.0.0
 * @since 23-10-2019
 */
class UserAccountActivity : AbstractBaseActivity() {

    private val userAccountActivityBinder by lazy {
        return@lazy UserAccountActivityBinder()
    }

    override fun setUpBuilder() = absActivityBuilder {
        contentView = R.layout.activity_user_account
        abstractBinding = userAccountActivityBinder
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.title_user_account_info)
    }
}