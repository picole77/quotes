package edu.itvo.quotescelebrities.data.remote

import com.google.gson.JsonObject
import edu.itvo.quotescelebrities.domain.model.QuoteModel
import retrofit2.http.*

interface QuoteApiInterface {
    @Headers("Content-Type: application/json; charset=utf-8","Accept: */*; charset=utf-8")
    @GET("api/v1/quotes")
    suspend fun getQuotes(@Header("Authorization") token:String): JsonObject



    @POST("api/v1/quotes")
    suspend fun addQuotes(): JsonObject

    @PUT("api/v1/quotes")
    suspend fun editQuotes(quote: QuoteModel): JsonObject

}
