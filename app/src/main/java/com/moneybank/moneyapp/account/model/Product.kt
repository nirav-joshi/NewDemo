package com.moneybank.moneyapp.account.model

data class Product(
    val AnnualLimit: Int,
    val CanWithdraw: Boolean,
    val CategoryType: String,
    val DepositLimit: Int,
    val FriendlyName: String,
    val Id: Int,
    val Name: String,
    val ProductHexCode: String,
    val Type: String
)