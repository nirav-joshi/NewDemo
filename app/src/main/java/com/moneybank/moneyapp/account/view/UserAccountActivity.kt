package com.example.minimoneybox.account.view

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moneybank.moneyapp.BR
import com.moneybank.moneyapp.R
import com.moneybank.moneyapp.account.model.ProductResponse
import com.moneybank.moneyapp.account.view.AccountViewModel
import com.moneybank.moneyapp.adapters.BindingRecyclerAdapter
import com.moneybank.moneyapp.base.AbstractBaseActivity
import com.moneybank.moneyapp.base.absActivityBuilder
import com.moneybank.moneyapp.login.view.LoginViewModel


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
    private val accountViewModel by lazy {
        ViewModelProviders.of(this)[AccountViewModel::class.java]
    }

    private var accountListAdapter: BindingRecyclerAdapter? = null

    override fun setUpBuilder() = absActivityBuilder {
        contentView = R.layout.activity_user_account
        abstractBinding = userAccountActivityBinder
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.title_user_account_info)
        setUpRecylerview()
        loadUsername()
        observerAccountDetails()
        loadAccountDetails()

    }

    private fun setUpRecylerview() {
        accountListAdapter = BindingRecyclerAdapter.Builder(ArrayList())
            .setLayoutResId(R.layout.item_list_plan)
            .onBindViewHolderCallback { holder, position, adapter ->
                val rowDto = adapter.list[holder.adapterPosition] as? ProductResponse
                holder.binding.setVariable(BR.productAccountDTO, rowDto)
            }
            .build()
        userAccountActivityBinder.binding?.rvPlans?.adapter = accountListAdapter
    }

    private fun observerAccountDetails() {
     accountViewModel.accountLiveData.observe(this, Observer {
         accountListAdapter?.addAllItems(it?.ProductResponses?.toMutableList())
     })
    }

    private fun loadAccountDetails() {
        accountViewModel.getAccountDetail()
    }

    private fun loadUsername() {
        intent?.extras?.let {
            if(!it.getString("name").isNullOrEmpty())
                userAccountActivityBinder.userName.set(it.getString("name"))
        }
    }
}