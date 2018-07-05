package com.mercadolibre.mutantes.utils;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.ArrayList;
import java.util.List;

public class RequestHeaders {
    //Headers Keys
    public static final String CONTENT_TYPE_KEY = "Content-Type";
    //Headers Values
    public static final String CONTENT_TYPE_VALUE = "application/json";

    public static Headers getRequestHeaders(){
        List<Header> list = new ArrayList<Header>();
        list.add(new Header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE));
        Headers headers = new Headers(list);
        return headers;
    }
}
