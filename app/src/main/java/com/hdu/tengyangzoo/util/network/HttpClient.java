package com.hdu.tengyangzoo.util.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {


    public static Retrofit createRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;

    }

    public static ApiService getApi(String url){
        ApiService apiService = HttpClient.createRetrofit(url).create(ApiService.class);
        return  apiService;
    }
}
