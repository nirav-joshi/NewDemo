package com.moneybank.moneyapp.account.data


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
}