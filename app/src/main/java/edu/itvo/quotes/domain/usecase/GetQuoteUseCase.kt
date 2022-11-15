package edu.itvo.quotes.domain.usecase

import edu.itvo.quotes.domain.QuoteRepository
import edu.itvo.quotes.domain.model.QuoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository){
    suspend fun getQuote(quoteId:Int): Flow<QuoteModel> = quoteRepository.getQuote(quoteId)

}