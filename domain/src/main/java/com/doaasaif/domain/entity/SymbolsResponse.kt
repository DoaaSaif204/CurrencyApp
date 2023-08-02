package com.doaasaif.domain.entity

data class SymbolsResponse(
    val success: Boolean,
    //val symbols:Symbols
    val symbols: Map<String, String>
)