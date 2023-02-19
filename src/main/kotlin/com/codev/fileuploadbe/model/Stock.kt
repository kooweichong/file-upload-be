package com.codev.fileuploadbe.model

import org.springframework.data.annotation.Id

data class Stock(
    @field:Id val id: Int?,
    val invoiceno: String,
    val stockcode: String,
    val description: String,
    val quantity: Int,
    val invoicedate: String,
    val unitprice: Float,
    val customerid: String,
    val country: String
)
