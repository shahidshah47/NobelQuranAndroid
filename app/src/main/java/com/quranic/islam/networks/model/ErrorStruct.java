package com.quranic.islam.networks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.quranic.islam.base.BaseModel;

public class ErrorStruct extends BaseModel {

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage= errorMessage;
    }
}
