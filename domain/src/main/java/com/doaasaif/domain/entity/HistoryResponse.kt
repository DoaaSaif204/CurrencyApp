package com.doaasaif.domain.entity

data class HistoryResponse(
    val base: String,
    val date: String,
    val historical: Boolean,
    val rates: RatesX,
    val success: Boolean,
    val timestamp: Int
)