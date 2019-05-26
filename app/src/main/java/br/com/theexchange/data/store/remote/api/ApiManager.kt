package br.com.theexchange.data.store.remote.api

import br.com.theexchange.BuildConfig
import br.com.theexchange.data.store.remote.api.service.CountryService
import br.com.theexchange.data.store.remote.api.service.ExchangeService
import retrofit2.Retrofit


class ApiManager private constructor(apiEndpoint: String, timeout: Int?) {

    private val retrofit: Retrofit = ApiAdapterFactory.createAdapter(apiEndpoint, timeout)

    private var countryService: CountryService? = null

    private var exchangeService: ExchangeService? = null

    fun getCountryService(): CountryService {
        if (countryService == null) {
            countryService = retrofit.create(CountryService::class.java)
        }
        return countryService!!
    }

    fun getExchangeService(): ExchangeService {
        if (exchangeService == null) {
            exchangeService = retrofit.create(ExchangeService::class.java)
        }
        return exchangeService!!
    }

    companion object {

        private var apiManager: ApiManager? = null

        val apiInstance: ApiManager
            @Synchronized get() {
                if (apiManager == null) {
                    apiManager = ApiManager(BuildConfig.API_ENDPOINT,  null)
                }
                return apiManager!!
            }
    }

}