package com.moneybank.moneyapp.account.model

data class ProductResponse(
    val CollectionDayMessage: String,
    val ContributionStatus: String,
    val Id: Int,
    val InvestorAccount: InvestorAccount,
    val IsFavourite: Boolean,
    val IsSelected: Boolean,
    val Moneybox: Int,
    val Personalisation: Personalisation,
    val PlanValue: Double,
    val Product: Product,
    val SubscriptionAmount: Int,
    val TotalFees: Double
)