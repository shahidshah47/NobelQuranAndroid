package com.quranic.islam;

import static com.quranic.islam.networks.ApiClient.retrofit;

import android.app.Activity;

import com.google.gson.Gson;
import com.quranic.islam.networks.ApiClient;
import com.quranic.islam.networks.ApiInterface;
import com.quranic.islam.networks.ServerCalls;
import com.quranic.islam.utils.PrefManager;

import retrofit2.Retrofit;

public class SingletonClass extends ServerCalls {
    private static SingletonClass sSoleInstance;
    private ApiInterface apiInterface;
    private Retrofit retrofit;
    private Gson gson;

    private SingletonClass() {

    }

    public static SingletonClass getInstance() {
        if (sSoleInstance == null) {
            sSoleInstance = new SingletonClass();
        }

        return sSoleInstance;
    }

    public ApiInterface getApiInterface(Activity mAct) {
        if (apiInterface == null)
            apiInterface = getClient(mAct).create(ApiInterface.class);
        return apiInterface;
    }

    public Retrofit getClient(Activity mAct) {
        if (retrofit == null)
            refreshClient(mAct, SERVER_TIMEOUT);
        return retrofit;
    }

    public void refreshClient(Activity mAct, int time) {
        ApiClient.CALL_TIMEOUT = time;
        ApiClient.CONNECT_TIMEOUT = time;
        ApiClient.READ_TIMEOUT = time;
        ApiClient.WRITE_TIMEOUT = time;

        retrofit = ApiClient.getRetrofit(mAct);
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public Gson gson() {
        if (gson == null)
            gson = new Gson();
        return gson;
    }
}
