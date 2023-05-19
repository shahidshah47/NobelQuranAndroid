package com.quranic.islam.utils;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.quranic.islam.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methods extends Constants {
    public void startActivity(Activity mAc, Class aClass, int status, int animationType) {
        if (status == START_ACTIVITY) {
            mAc.startActivity(new Intent(mAc, aClass));
        } else if (status == START_ACTIVITY_WITH_FINISH) {
            mAc.startActivity(new Intent(mAc, aClass));
            mAc.finish();
        } else if (status == START_ACTIVITY_WITH_CLEAR_BACK_STACK) {
            mAc.startActivity(new Intent(mAc, aClass).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else if (status == START_ACTIVITY_WITH_TOP) {
            mAc.startActivity(new Intent(mAc, aClass).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }

        startAnimation(mAc, animationType);
    }

    public void startActivityWithDataBundle(Activity mAc, Class aClass, Bundle bundle, int status, int animationType) {
        if (status == START_ACTIVITY) {
            mAc.startActivity(new Intent(mAc, aClass).putExtras(bundle));
        } else if (status == START_ACTIVITY_WITH_FINISH) {
            mAc.startActivity(new Intent(mAc, aClass).putExtras(bundle));
            mAc.finish();
        } else if (status == START_ACTIVITY_WITH_CLEAR_BACK_STACK) {
            mAc.startActivity(new Intent(mAc, aClass).putExtras(bundle).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else if (status == START_ACTIVITY_WITH_TOP) {
            mAc.startActivity(new Intent(mAc, aClass).putExtras(bundle).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }

        startAnimation(mAc, animationType);
    }

    public void finishActivity(Activity mAc, int animationType) {
        mAc.finish();
        startAnimation(mAc, animationType);
    }

    private void startAnimation(Activity mAc, int animationType) {
        if (animationType == ANIMATION_FORWARD) {
            mAc.overridePendingTransition(R.anim.trans_left_in,
                    R.anim.trans_left_out);
        } else if (animationType == ANIMATION_BACKWARD) {
            mAc.overridePendingTransition(R.anim.trans_right_in,
                    R.anim.trans_right_out);
        }
    }

    public void hideKeyboard(Activity mAc) {
        try {
            InputMethodManager imm = (InputMethodManager) mAc.getSystemService(INPUT_METHOD_SERVICE);
            if (imm == null) throw new AssertionError("assertion Error! Imm is null");
            imm.hideSoftInputFromWindow(Objects.requireNonNull(mAc.getCurrentFocus()).getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    public void hideKeyboardDialog(Activity mAc, Dialog dialog) {
        try {
            InputMethodManager imm = (InputMethodManager) mAc.getSystemService(INPUT_METHOD_SERVICE);
            if (imm == null) throw new AssertionError("assertion Error! Imm is null");
            imm.hideSoftInputFromWindow(Objects.requireNonNull(dialog.getCurrentFocus()).getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    public String getTimeZone() {
        return TimeZone.getDefault().getID();
    }

    public boolean isEmpty(String str) {
        if (str != null && !str.equals(""))
            return false;

        return true;
    }

    public boolean isValidEmail(String emailStr) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailStr);

        return matcher.matches();
    }

    public boolean isValidPassword(String passwordStr) {
        if (passwordStr != null && passwordStr.length() >= 6) {
            return true;
        }

        return false;
    }

    public boolean isEqual(String str1, String str2) {
        if (str1 != null && str2 != null && str1.equals(str2)) {
            return true;
        }

        return false;
    }

    public void openURL(Activity mAct, String url) {
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "http://" + url;
        }

        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mAct.startActivity(openUrlIntent);
    }

    public String convertDateIntoAppFormat(String dateStr) {
        try {
            Date myDate = defaultDateFormat.parse(dateStr);
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
//            timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return timeFormat.format(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateStr;
    }

    public String getCurrentDate() {
        return dashDateFormat.format(Calendar.getInstance().getTime());
    }

    public String getEnglishCurrentDate() {
        return englishDateFormat.format(Calendar.getInstance().getTime());
    }

    public String getIslamicCurrentDate() {
        return arabicDateFormat.format(Calendar.getInstance().getTime());
    }

    public String convertDateIntoRequiredFormat(String dateStr) {
        if (dateStr != null && !dateStr.equals("")) {
            try {
                Date myDate = apiDateFormat.parse(dateStr);
                return dateFormat.format(myDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dateStr;
        }
        return "";
    }

    public String convertDateIntoRequiredFormat(String dateStr, SimpleDateFormat requiredFormat) {
        if (dateStr != null && !dateStr.equals("")) {
            try {
                Date myDate = apiDateFormat.parse(dateStr);
                return requiredFormat.format(myDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dateStr;
        }
        return "";
    }

    public String convertServerIntoDateFormat(String dateStr) {
        try {
            Date myDate = apiDateFormat.parse(dateStr);
            return dateFormat.format(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public String convertDateTimeStampFormat(String dateStr) {
        try {
            Date myDate = dateFormat.parse(dateStr);
            return apiDateFormat.format(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }
}
