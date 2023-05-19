package com.quranic.islam.networks;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {

    @Headers("Accept: application/json")
    @GET()
    Call<Object> getCall(@Url String url);

    @Headers("Content-Type: application/json")
    @GET()
    Call<Object> getCall(@Header("Token") String bearerToken, @Url String url);

    @Headers("Accept: application/json")
    @GET()
    Call<Object> getCall(@Header("Token") String bearerToken,@Url String url, @Query("pageNumber") int pageIndex);

    @Headers("Accept: application/json")
    @DELETE()
    Call<Object> deleteCall(@Url String url);

    @Headers("Content-Type: application/json")
    @GET()
    Call<Object> getCall(@Url String url, @QueryMap Map<String, Object> params);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCall(@Url String url);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCall(@Url String url, @Body String body);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCal(@Header("Token") String bearerToken, @Url String url);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCal(@Header("Token") String bearerToken, @Url String url, @Body String body);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCall(@Url String url, @Body int body);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCall(@Url String url, @QueryMap Map<String, Object> params, @Body String body);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCall(@Header("Token") String bearerToken, @Url String url, @Body String body);

    @Headers("Accept: application/json")
    @POST()
    Call<Object> postCall(@Url String url, @QueryMap Map<String, Object> params);

    @Headers("Accept: application/json")
    @PUT()
    Call<Object> putCall(@Url String url, @QueryMap Map<String, Object> params);


    @Headers("Accept: application/json")
    @DELETE()
    Call<Object> deleteCall(@Url String url, @QueryMap Map<String, Object> params);

    @Headers("Accept: application/json")
    @DELETE()
    Call<Object> deleteCall(@Header("Token") String bearerToken, @Url String url);


    @Headers("Accept: application/json")
    @Multipart
    @POST()
    Call<Object> postCall(@Url String url, @Part List<MultipartBody.Part> media, @QueryMap Map<String, Object> params);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCall(@Header("Authorization") String bearerToken, @Url String url, @QueryMap Map<String, Object> params);

    @Headers("Content-Type: application/json")
    @POST()
    Call<Object> postCall(@Header("X-CSRF-TOKEN") String bearerToken, @QueryMap() Map<String, RequestBody> list, @Url String url, @QueryMap Map<String, String> params);


    @Headers("Content-Type: application/json")
    @PUT()
    Call<Object> putCall(@Url String url, @Body String body);

    @Headers("Content-Type: application/json")
    @PATCH()
    Call<Object> patchCall(@Url String url, @Body String body);

    @Headers("Accept: application/json")
    @Multipart
    @POST()
    Call<Object> postCall(@Url String url, @Part MultipartBody.Part media, @QueryMap Map<String, Object> params);

    @Headers("Accept: application/json")
    @Multipart
    @POST()
    Call<Object> postCall(@Header("Authorization") String bearerToken, @Url String url, @Part List<MultipartBody.Part> media, @QueryMap Map<String, Object> params);

    @Multipart
    @POST()
    Call<Object> postCall(@Url String url, @Part("image\"; filename=\"image.jpg\" ") RequestBody media, @QueryMap Map<String, Object> params);
}
