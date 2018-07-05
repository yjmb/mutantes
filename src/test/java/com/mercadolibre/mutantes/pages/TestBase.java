package com.mercadolibre.mutantes.pages;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
    protected static final long TIME = 3600;
    protected RequestSpecification request;
    protected Response response;

}
