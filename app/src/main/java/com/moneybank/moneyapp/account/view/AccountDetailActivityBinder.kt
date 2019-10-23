package com.moneybank.moneyapp.account.view

import com.moneybank.moneyapp.base.AbstractBinding
import com.moneybank.moneyapp.databinding.ActivityAccountDetailBinding


/**
 * [AccountDetailActivityBinder] :
 *
 *
 * @author Jeel Vankhede
 * @version 1.0.0
 * @since 23-10-2019
 */
class AccountDetailActivityBinder : AbstractBinding<ActivityAccountDetailBinding>() {

    override fun onCreated() {
        binding?.accountDetailBinder = this
    }

    override fun onDestroy() {
        binding?.accountDetailBinder = null
    }
}