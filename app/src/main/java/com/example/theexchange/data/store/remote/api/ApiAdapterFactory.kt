package com.example.theexchange.data.store.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException

import java.util.concurrent.TimeUnit
import javax.net.ssl.*
import okhttp3.logging.HttpLoggingInterceptor
import java.security.cert.X509Certificate


internal class ApiAdapterFactory private constructor(private val apiEndpoint: String, private val timeout: Int?) {

    private fun buildAdapter(): Retrofit {
        val client = okHttpClient.newBuilder()

        //if necessary put header in the request
        //client.addInterceptor(getHeaderInterceptor());

        if (timeout != null) {
            client.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        }

        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        client.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(apiEndpoint)
            .client(client.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {

        fun createAdapter(apiEndpoint: String, timeout: Int?): Retrofit {
            val factory = ApiAdapterFactory(apiEndpoint, timeout)
            return factory.buildAdapter()
        }
    }

    private val okHttpClient: OkHttpClient
        get() {
            try {
                val trustManager = object : X509TrustManager {

                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {

                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {

                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }

                val hostnameVerifier = HostnameVerifier { hostname, session -> true }

                val sc = SSLContext.getInstance("SSL")
                sc.init(null, arrayOf<TrustManager>(trustManager), java.security.SecureRandom())

                val client = OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                client.sslSocketFactory(sc.socketFactory, trustManager)
                client.hostnameVerifier(hostnameVerifier)

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(interceptor)

                return client.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }



}