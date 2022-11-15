package edu.itvo.quotes.domain.usecase

import edu.itvo.quotes.domain.QuoteRepository
import edu.itvo.quotes.domain.model.QuoteModel
import javax.inject.Inject


class GetQuoteAddUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    //suspend fun  getQuoteRandom(): Flow<QuoteModel> = quoteRepository.getQuoteRandom()
    suspend fun  addQuote(quoteModel: QuoteModel)=quoteRepository.addQuote(quoteModel)
}