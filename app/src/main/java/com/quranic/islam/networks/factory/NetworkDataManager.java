package com.quranic.islam.networks.factory;

import android.app.Activity;

import com.quranic.islam.interfaces.ApiCallListener;

import retrofit2.Call;
import retrofit2.Retrofit;

public class NetworkDataManager {
    private Activity mAct;
    private Call<Object> call;
    private ApiCallListener listener;
    private Retrofit retrofit;
    private boolean isTokenNeedToCheck;
    private boolean isSignUp;

    private String error;

    public NetworkDataManager() {
        this.isSignUp = false;
    }

    public NetworkDataManager(Activity mAct) {
        this.mAct = mAct;
        this.isSignUp = false;
    }

    public Activity getAct() {
        return mAct;
    }

    public Call<Object> getCall() {
        return call;
    }

    public void setCall(Call<Object> call) {
        this.call = call;
    }

    public ApiCallListener getListener() {
        return listener;
    }

    public void setListener(ApiCallListener listener) {
        this.listener = listener;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public boolean isTokenNeedToCheck() {
        return isTokenNeedToCheck;
    }

    public void setTokenNeedToCheck(boolean tokenNeedToCheck) {
        isTokenNeedToCheck = tokenNeedToCheck;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSignUp() {
        return isSignUp;
    }

    public void setSignUp(boolean signUp) {
        isSignUp = signUp;
    }
}
