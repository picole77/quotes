package edu.itvo.quotes.domain.usecase

import edu.itvo.quotes.domain.QuoteRepository
import javax.inject.Inject


class GetAllQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun getAllQuote() = quoteRepository.getAllQuote()
}