package com.quranic.islam.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.quranic.islam.interfaces.OnDialogCallBack;

import es.dmoral.toasty.Toasty;

public class DialogFactory extends Methods {
    private ProgressDialog progressDialog;

    public void showProgressDialog(Context mContext) {
        dismissProgress();
        progressDialog = new ProgressDialog(mContext, ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void showToast(Activity mAct, String message, int type) {
        if (type == DEFAULT_TOAST) {
            Toasty.normal(mAct, message).show();
        } else if (type == TOAST_ERROR) {
            Toasty.error(mAct, message, Toast.LENGTH_SHORT, true).show();
        } else if (type == TOAST_SUCCESS) {
            Toasty.success(mAct, message, Toast.LENGTH_SHORT, true).show();
        } else if (type == TOAST_INFO) {
            Toasty.info(mAct, message, Toast.LENGTH_SHORT, true).show();
        } else if (type == TOAST_WARNING) {
            Toasty.warning(mAct, message, Toast.LENGTH_SHORT, true).show();
        }
    }

    public void showToast(Activity mAct, String message, int type, boolean isShow) {
        if (mAct != null && message != null && isShow) {
            if (type == DEFAULT_TOAST) {
                Toasty.normal(mAct, message).show();
            } else if (type == TOAST_ERROR) {
                Toasty.error(mAct, message, Toast.LENGTH_SHORT, true).show();
            } else if (type == TOAST_SUCCESS) {
                Toasty.success(mAct, message, Toast.LENGTH_SHORT, true).show();
            } else if (type == TOAST_INFO) {
                Toasty.info(mAct, message, Toast.LENGTH_SHORT, true).show();
            } else if (type == TOAST_WARNING) {
                Toasty.warning(mAct, message, Toast.LENGTH_SHORT, true).show();
            }
        }
    }

    public void showDialogWith2Buttons(Context mContext, OnDialogCallBack callBack, String messageStr) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(messageStr)
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    if (callBack != null)
                        callBack.OnDialogCallBack(true);
                    dialog.cancel();
                })
                .setNegativeButton("No", (dialog, id) -> {
                    if (callBack != null)
                        callBack.OnDialogCallBack(false);
                    dialog.cancel();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
