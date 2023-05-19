package com.quranic.islam.networks;


import com.quranic.islam.networks.model.ErrorSubStruct;

import java.util.List;

public class APIError {
    private String status;
    private String message;
    private List<ErrorSubStruct> errors;
    private long code;
    private String path;
    private String service;
    private String timestamp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorSubStruct> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorSubStruct> errors) {
        this.errors = errors;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
