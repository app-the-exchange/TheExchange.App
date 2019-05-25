package com.example.theexchange.data.store.remote.api;

import retrofit2.Retrofit;


public class ApiManager {

    //Default endpoint
    private static ApiManager apiManager;

    private Retrofit retrofit;

    private static String ENDPOINT = "";


    private ApiManager(String apiEndpoint, boolean encrypted, Integer timeout) {
        retrofit = ApiAdapterFactory.createAdapter(apiEndpoint, timeout);
    }

    public static synchronized ApiManager getApiInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager(ENDPOINT, false, null);
        }
        return apiManager;
    }

}