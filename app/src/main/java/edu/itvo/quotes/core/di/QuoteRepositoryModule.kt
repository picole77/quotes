package edu.itvo.quotes.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.itvo.quotes.data.QuoteRepositoryImpl
import edu.itvo.quotes.data.local.QuoteLocalDataSource
import edu.itvo.quotes.data.local.QuoteLocalDataSourceImpl
import edu.itvo.quotes.domain.QuoteRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class QuoteRepositoryModule {

    @Binds
    abstract fun bindQuoteRepository(quoteRepositoryImpl: QuoteRepositoryImpl):
            QuoteRepository

    @Binds
    abstract fun bindQuoteLocalDataSource(quoteLocalDataSourceImpl: QuoteLocalDataSourceImpl):
            QuoteLocalDataSource

}