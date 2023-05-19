package com.quranic.islam.networks.factory;

import com.quranic.islam.networks.APIError;

public class ApiResponse<T> {
    private T data;
    private APIError apiError;
    private String message;
    private boolean success;
    private String status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public APIError getApiError() {
        return apiError;
    }

    public void setApiError(APIError apiError) {
        this.apiError = apiError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
