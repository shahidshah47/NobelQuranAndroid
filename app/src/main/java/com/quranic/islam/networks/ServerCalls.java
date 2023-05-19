package com.quranic.islam.networks;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.quranic.islam.SingletonClass;
import com.quranic.islam.activities.SplashActivity;
import com.quranic.islam.networks.factory.NetworkDataManager;
import com.quranic.islam.networks.model.ErrorSubStruct;
import com.quranic.islam.utils.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class ServerCalls extends PrefManager {
    public void makePostCall(NetworkDataManager dataManager) {
        try {
            dataManager.getCall().enqueue(new Callback<Object>() {
                @Override
                public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                    String responseStr = ApiClient.getGSONBuilder().toJson(response.body());
                    if (response.isSuccessful()) {
//                        dataManager.getListener().onCallRes(responseStr, "", null, true);
                        parseResponseAndCallback(dataManager, responseStr, dataManager.getError(), true);
                    } else {
                        APIError error = parseError(dataManager.getAct(), response);
                        if (error != null && error.getErrors() != null)
                            dataManager.getListener().onCallRes(responseStr, "", error.getErrors(), false);
                        else
                            parseResponseAndCallback(dataManager, responseStr, dataManager.getError(), false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                    responseCallBack(dataManager, "", t.getMessage(), false);
                }
            });
        } catch (Exception e) {
            responseCallBack(dataManager, "", e.getMessage(), false);
        }
    }

    private void parseResponseAndCallback(NetworkDataManager dataManager, String resStr, String messageStr, boolean isSuccess) {
        try {
            JSONObject responseOBJ = new JSONObject(resStr);
            if (responseOBJ != null) {
                int statusCode = responseOBJ.optInt("StatusCode");
                JSONArray errorARR = responseOBJ.optJSONArray("ErrorList");

                if (errorARR != null && errorARR.length() > 0) {
                    String responseMessageStr = errorARR.optString(0);
                    if (statusCode == 600) {
                        if (responseMessageStr != null)
                            dataManager.getListener().onCallRes(resStr, responseMessageStr, null, false);
                        removePref(dataManager.getAct(), TOKEN_PREF);
                        removePref(dataManager.getAct(), USER_PREF);
                        removePref(dataManager.getAct(), IS_LOGIN_PREF);

                        startActivity(dataManager.getAct(), SplashActivity.class, START_ACTIVITY_WITH_FINISH, ANIMATION_FORWARD);
                    } else if (statusCode == 200 && dataManager.isSignUp()) {
                        List<ErrorSubStruct> errors = new ArrayList<>();
                        ErrorSubStruct struct = new ErrorSubStruct();
                        struct.setMessage(responseMessageStr);
                        errors.add(struct);

                        dataManager.getListener().onCallRes(resStr, responseMessageStr, errors, true);
                    } else if (responseMessageStr != null) {
                        dataManager.getListener().onCallRes(resStr, responseMessageStr, null, false);
                    } else {
                        dataManager.getListener().onCallRes(resStr, messageStr, null, false);
                    }
                } else {
                    dataManager.getListener().onCallRes(resStr, messageStr, null, isSuccess);
                }
            } else {
                dataManager.getListener().onCallRes(resStr, messageStr, null, isSuccess);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            dataManager.getListener().onCallRes(resStr, e.getMessage(), null, false);
        }
    }

    public APIError parseError(Activity mAct, Response<?> response) {
        APIError error = null;
        try {
            Converter<ResponseBody, APIError> converter =
                    SingletonClass.getInstance().getClient(mAct).responseBodyConverter(APIError.class, new Annotation[0]);
            error = converter.convert(response.errorBody());
        } catch (IllegalStateException | JsonSyntaxException | IOException exception) {
            exception.getMessage();
            error = null;
        }

        return error;
    }

    private void responseCallBack(NetworkDataManager dataManager, String resStr, String messageStr, boolean isSuccess) {
        if (messageStr.contains("No address associated with hostname"))
            messageStr = "Please check your Internet Connection";
        dataManager.getListener().onCallRes(resStr, messageStr, null, isSuccess);
    }
}