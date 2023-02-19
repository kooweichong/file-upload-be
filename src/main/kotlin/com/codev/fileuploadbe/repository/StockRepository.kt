package com.codev.fileuploadbe.repository

import com.codev.fileuploadbe.model.Stock
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository : CoroutineCrudRepository<Stock, Int> {
}