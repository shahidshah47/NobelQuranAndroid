package com.quranic.islam;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.quranic.islam.utils.data.QDH;
import com.quranic.islam.utils.data.QuranArabicText;

public class MyApplication extends Application {
    public QDH qdh;
    public QuranArabicText quranArabicText;
    public String[] quranTranslationData;
    public String[] quranTafseerData;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        SingletonClass.getInstance();
        init();
    }

    private void init() {
        qdh = new QDH();
        quranArabicText = new QuranArabicText();
        quranTranslationData = getResources().getStringArray(R.array.translation);
        quranTafseerData = getResources().getStringArray(R.array.tafseer);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
