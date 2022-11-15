package edu.itvo.quotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itvo.quotes.domain.model.QuoteModel
import edu.itvo.quotes.domain.usecase.GetQuoteAddUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddViewModel
@Inject constructor(
    private val getQuoteAddUseCase: GetQuoteAddUseCase
) : ViewModel() {

    fun addQuote(quoteModel: QuoteModel) {
        viewModelScope.launch {
            getQuoteAddUseCase.addQuote(quoteModel)
        }
    }

}