package com.example.m7_geeco_in.data

import com.example.m7_geeco_in.Despesses
import com.example.m7_geeco_in.Ingressos
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

data class IngresRequest(
    val title: String,
    val description: String,
    val amount: Int,
    val date: String
)

data class DespesaRequest(
    val title: String,
    val description: String,
    val amount: Int,
    val date: String
)

interface RetrofitService {
    @GET("incomes/")
    suspend fun getIngressosList(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): List<Ingressos>

    @GET("expenses/")
    suspend fun getDespesesList(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): List<Despesses>

    @GET("incomes/{income_id}")
    suspend fun ingresId( @Path("income_id") idIngres:Int):Ingressos

    @GET("expenses/{expenses_id}")
    suspend fun despesesId( @Path("expenses_id") idDespesa:Int):Despesses

    @POST("incomes/")
    suspend fun postIngres(@Body request: IngresRequest): IngresRequest

    @POST("expenses/")
    suspend fun postExpanses(@Body request: DespesaRequest): DespesaRequest

    @DELETE("incomes/{income_id}")
    suspend fun deleteIngres(@Path("income_id") idIngres: Int): Response<Unit>

    @DELETE("/expenses/{expense_id}")
    suspend fun deleteExpese(@Path("expense_id") idExpese: Int): Response<Unit>
}

class geecoinAPI {
    companion object {
        private var mAPI: RetrofitService? = null

        @Synchronized
        fun API(): RetrofitService {
            if (mAPI == null) {
                val unsafeClient = getUnsafeOkHttpClient()

                mAPI = Retrofit.Builder()
                    .client(unsafeClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://54.173.54.56")
                    .build()
                    .create(RetrofitService::class.java)
            }
            return mAPI!!
        }
    }
}


private fun getUnsafeOkHttpClient(): OkHttpClient {
    try {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }

        val okHttpClient = builder.build()
        return okHttpClient
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}
