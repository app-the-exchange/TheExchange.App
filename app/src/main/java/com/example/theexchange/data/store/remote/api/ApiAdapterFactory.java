package com.example.theexchange.data.store.remote.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;


class ApiAdapterFactory {

    private String apiEndpoint;
    private Integer timeout;

    private ApiAdapterFactory(String apiEndpoint, Integer timeout) {
        this.apiEndpoint = apiEndpoint;
        this.timeout = timeout;
    }


    /*
        Instance
     */
    static Retrofit createAdapter(String apiEndpoint, Integer timeout) {
        ApiAdapterFactory factory = new ApiAdapterFactory(apiEndpoint, timeout);
        return factory.buildAdapter();
    }

    /*
        Build
     */
    private Retrofit buildAdapter() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        //client.addInterceptor(getHeaderInterceptor());

        if (timeout != null) {
            client.readTimeout(timeout, TimeUnit.SECONDS);
        }

        return new Retrofit.Builder()
                .baseUrl(apiEndpoint)
                .client(client.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}