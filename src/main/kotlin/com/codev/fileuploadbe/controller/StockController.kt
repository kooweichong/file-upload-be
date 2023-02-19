package com.codev.fileuploadbe.controller

import com.codev.fileuploadbe.model.Stock
import com.codev.fileuploadbe.repository.StockRepository
import com.codev.fileuploadbe.service.StockService
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerResponse

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class StockController(private val stockRepository: StockRepository, private val stockService: StockService) {
    

    // Coroutine for Flux
    @FlowPreview
    @GetMapping("/stocks")
    suspend fun retrieveAllStocks(): Flow<Stock> {
        return stockService.retrieveAllStocks()
    }

    @PostMapping("/uploads")
    suspend fun uploadFIle(@RequestPart("file") file: FilePart): String? {
        stockService.uploadCSV(file)
        return "done"
    }

}