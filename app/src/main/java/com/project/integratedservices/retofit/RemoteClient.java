package com.project.integratedservices.retofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.supportClasses.IntegratedServiceApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteClient {


//    public static final String BASE_URL = "http://www.webzeminiprint.com/api/";
    public static final String BASE_URL = "http://www.webzeminiprint.in/api/";
    public static RetrofitApis retrofitApi = null;


    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new ApiInterceptor(IntegratedServiceApplication.getsInstance()))
            .readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .build();

    public static RetrofitApis getRetrofitApi() {
        if (retrofitApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
            retrofitApi = retrofit.create(RetrofitApis.class);
        }
        return retrofitApi;
    }

}
