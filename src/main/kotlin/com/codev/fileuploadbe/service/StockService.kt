package com.codev.fileuploadbe.service

import com.codev.fileuploadbe.model.Stock
import com.codev.fileuploadbe.repository.StockRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactive.awaitFirst
import mu.KotlinLogging
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

@Service
class StockService(private val stockRepository: StockRepository) {

    private val logger = KotlinLogging.logger {}
    suspend fun retrieveAllStocks(): Flow<Stock> {
        return stockRepository.findAll()
    }

    suspend fun getAllStocks(request: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(
                stockRepository.findAll()
            )
    }

    suspend fun uploadCSV(file: FilePart) {
        try {

            val inputstreamFIle = file.content().map { it -> it.asInputStream() }.awaitFirst()
            val stockList: List<Stock> = readCsv(inputstreamFIle)

            stockRepository.saveAll(stockList.asFlow()).collect { println(it) }
        } catch (exception: Exception) {
            logger.error { exception.message }
        }
    }

    fun readCsv(inputStream: InputStream?): List<Stock> {
        val stockList = mutableListOf<Stock>()
        try {
            val fileReader: BufferedReader = BufferedReader(InputStreamReader(inputStream))
            val csvParser: CSVParser =
                CSVParser(fileReader, CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())
            val csvRecords = csvParser.records

            for (record in csvRecords) {
                if (record.get(0) == "")
                    continue
                if (record.size() != 8)
                    continue
                val stock = Stock(
                    null,
                    record.get(0),
                    record.get(1),
                    record.get(2),
                    record.get(3).trim().toInt(),
                    record.get(4),
                    record.get(5).toFloat(),
                    record.get(6),
                    record.get(7)
                )
                stockList.add(stock)
            }
        } catch (exception: Exception) {
            logger.error { exception.message }
        }

        return stockList
    }

}