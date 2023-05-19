package com.quranic.islam.networks;


import static com.quranic.islam.utils.Constants.BASE_URL;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.quranic.islam.SingletonClass;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;
    public static int CALL_TIMEOUT = 50;
    public static int CONNECT_TIMEOUT = 50;
    public static int READ_TIMEOUT = 50;
    public static int WRITE_TIMEOUT = 50;

    private static final String TAG = ApiClient.class.getSimpleName();
    private final static String TOKEN = "Authorization";

    public static Retrofit getRetrofit(Activity mAct) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClient(mAct))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(getGSONBuilder())).build();
        }

        return retrofit;
    }

    public static Gson getGSONBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(Double.class,
                        (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
                            if (src == src.longValue())
                                return new JsonPrimitive("" + src.longValue());
                            return new JsonPrimitive("" + src);
                        })
                .setLenient().create();
    }

    private static Interceptor getExtraHeaderInterceptor(Activity mAct) {
        return chain -> {
            SingletonClass ST = SingletonClass.getInstance();
            String deviceID = ST.getMyStringPref(mAct, "DEVICE_ID", "6465465465456");
            String tokenStr = ST.getMyStringPref(mAct, ST.TOKEN_PREF, "");
            if (tokenStr.length() > 0)
                tokenStr = "Bearer " + tokenStr;
            Request newRequest = chain.request().newBuilder()
                    .addHeader("NST", ST.NST)
                    .addHeader("DeviceId",deviceID)
                    .addHeader("Content-Type", "application/json")
                    .build();

            return chain.proceed(newRequest);
        };
    }

    private static OkHttpClient getClient(Activity mAct) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().addInterceptor(getExtraHeaderInterceptor(mAct));
        okHttpClient.retryOnConnectionFailure(true);
       okHttpClient.addInterceptor(interceptor);

        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            okHttpClient.callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return okHttpClient.build();
    }
}
