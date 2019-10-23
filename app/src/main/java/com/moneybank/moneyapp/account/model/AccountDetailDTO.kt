package com.moneybank.moneyapp.account.model

data class AccountDetailDTO(
    val MoneyboxEndOfTaxYear: String,
    val ProductResponses: List<ProductResponse>,
    val TotalContributionsNet: Int,
    val TotalEarnings: Double,
    val TotalEarningsAsPercentage: Double,
    val TotalPlanValue: Double
)