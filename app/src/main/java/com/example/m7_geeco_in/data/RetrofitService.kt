package com.example.m7_geeco_in.data

import com.example.m7_geeco_in.Despesses
import com.example.m7_geeco_in.Ingressos
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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
    suspend fun llistaIngressos( @Path("income_id") idIngres:Int):List<Ingressos>

    @POST("incomes/")
    suspend fun postIngres(
        @Body title: String?,
        @Body description: String?,
        @Body amount: Int?,
        @Body date: String?
    ): Call<Ingressos>


    @POST("expenses/")
    suspend fun postDespese(
        @Field("title") title: String?,
        @Field("description") description: String?,
        @Field("amount") amount: Int?,
        @Field("date") date: String?
    ): Call<Despesses>
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
        // Create a trust manager that does not validate certificate chains
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
        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
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