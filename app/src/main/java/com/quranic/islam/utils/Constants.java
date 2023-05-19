package com.quranic.islam.utils;

import java.text.SimpleDateFormat;

public class Constants {
    public static String BASE_URL = "";
    public final int SERVER_TIMEOUT = 50;

    public final int DEFAULT_TOAST = 0;
    public final int TOAST_ERROR = 1;
    public final int TOAST_SUCCESS = 2;
    public final int TOAST_INFO = 3;
    public final int TOAST_WARNING = 4;

    public SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
    public SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat dashDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public SimpleDateFormat appDateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    public SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
    public SimpleDateFormat dateMonth = new SimpleDateFormat("MMM dd");
    public SimpleDateFormat englishDateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");
    public SimpleDateFormat arabicDateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");

    public final int UNKNOWN = -1;
    public final int RESULT_CODE = 1;
    public final int ERROR = 0;
    public final int SUCCESS = 1;
    public final int WARNING = 2;
    public final int DEFAULT = 3;

    public final int START_ACTIVITY = 0;
    public final int START_ACTIVITY_WITH_FINISH = 1;
    public final int START_ACTIVITY_WITH_CLEAR_BACK_STACK = 2;
    public final int START_ACTIVITY_WITH_TOP = 3;
    public final int FINISH_CURRENT_ACTIVITY = 4;

    public final int SHOW_PROGRESS = 0;
    public final int SHOW_DIALOG = 1;

    public final int ANIMATION_TYPE_NONE = 0;
    public final int ANIMATION_TYPE_DEFAULT = 1;
    public final int ANIMATION_FORWARD = 2;
    public final int ANIMATION_BACKWARD = 3;
    public final int ANIMATION_BLUR = 4;

    public final int SURAH_TYPE = 1;
    public final int PARAH_TYPE = 2;

    //Preferences
    public final String APP_SHARED_PREFERENCE = "QURANIC_APP_SHARED_PREFERENCE";
    public final String TOKEN_PREF = "TOKEN_PREF";
    public final String USER_PREF = "USER_PREF";
    public final String IS_LOGIN_PREF = "IS_LOGIN_PREF";
    public final String IS_ORDER_COMPLETED_PREF = "IS_ORDER_COMPLETED_PREF";

    public final String NST = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.qdnNFEysRSenoFOmQqPZm5F1VY8Cp5Jmis1d9uFkM-s";
    public final String CART_PREF = "CART_PREF";

    //Keys
    public final String OBJ_KEY = "OBJ_KEY";
    public final String REQUEST_FRAGMENT = "req_frag";
    public static final String FRAGMENT_PARAM_1 = "FRAGMENT_PARAM_1";
    public static final String FRAGMENT_PARAM_2 = "FRAGMENT_PARAM_2";
    public static final String FRAGMENT_PARAM_3 = "FRAGMENT_PARAM_3";
}
