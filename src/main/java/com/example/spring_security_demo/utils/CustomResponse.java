package com.example.spring_security_demo.utils;

import java.util.HashMap;
import java.util.Map;

public class CustomResponse<T> {
    public CustomResponse() {
    }

    Map<String,Object> response =new HashMap<String,Object>();

    public Map<String,Object> getResponseMessage(String status,String message,T data) {
        response.put("status",status);
        response.put("message",message);
        if(data != null) response.put("data",data);
        return response;
    }
}
