package com.quranic.islam.networks.factory;

import android.util.Log;

import com.quranic.islam.networks.APIError;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BackendUtils {
    private static final String TAG = BackendUtils.class.getSimpleName();

    @SuppressWarnings("unchecked")
    private static List<Integer> sSuccessfulResponseCodes = Arrays.asList(
            HttpURLConnection.HTTP_OK,
            207,
            201,
            204);

    @SuppressWarnings("unchecked")
    public static <T> ApiResponse<T> handleResponse(Response response, Retrofit retrofit) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        if (sSuccessfulResponseCodes.contains(response.code())) {
            apiResponse.setData((T) response.body());
            apiResponse.setSuccess(true);
        } else {
            // some error occurred in backend
            try {
                apiResponse.setSuccess(false);
                APIError apiError = webServiceError(response.errorBody(), retrofit);
                apiResponse.setApiError(apiError);
                apiResponse.setMessage(apiError.getMessage());
                apiResponse.setStatus(apiError.getStatus());
            } catch (ClassCastException ex) {
                Log.e(TAG, "handleResponse: Cannot cast backend error object to ApiError", ex);
                Log.e(TAG, "handleResponse: Data ==> " + response.body());
            } catch (NullPointerException ex) {
                Log.e(TAG, "handleResponse: NullPointerException in  ApiError", ex);
                Log.e(TAG, "handleResponse: Data ==> " + response.body());
            }
        }
        return apiResponse;
    }

    @SuppressWarnings("unchecked")
    public static APIError webServiceError(ResponseBody response, Retrofit mRetrofit) {
        try {
            Converter<ResponseBody, APIError> errorConverter = mRetrofit.responseBodyConverter(APIError.class, new Annotation[0]);
            return errorConverter.convert(response);
        } catch (IOException e) {
            e.printStackTrace();
            return new APIError();
        }
    }
}
