package com.quranic.islam.interfaces;

import com.quranic.islam.networks.model.ErrorSubStruct;

import java.util.List;

public interface ApiCallListener {
 void onCallRes(String response, String message, List<ErrorSubStruct> errors, boolean isSuccess);
}