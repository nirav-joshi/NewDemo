package com.example.minimoneybox.account.view

import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import com.moneybank.moneyapp.R
import com.moneybank.moneyapp.adapters.BindingRecyclerAdapter
import com.moneybank.moneyapp.base.AbstractBinding
import com.moneybank.moneyapp.databinding.ActivityUserAccountBinding


/**
 * [UserAccountActivityBinder] :
 *
 *
 * @author Jeel Vankhede
 * @version 1.0.0
 * @since 23-10-2019
 */
class UserAccountActivityBinder : AbstractBinding<ActivityUserAccountBinding>() {
    val userName = ObservableField<String?>()
    var plansAdapter: BindingRecyclerAdapter? = null
        private set

    override fun onCreated() {
        binding?.userAccountBinder = this
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        plansAdapter = BindingRecyclerAdapter.Builder()
            .setLayoutResId(R.layout.item_list_plan)
            .onBindViewHolderCallback { holder, pos, adapter -> }
            .build()
        binding?.rvPlans?.adapter = plansAdapter
        binding?.rvPlans?.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroy() {
        binding?.userAccountBinder = null
    }
}