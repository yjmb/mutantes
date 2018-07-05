package com.mercadolibre.mutantes.pages;

import com.mercadolibre.mutantes.utils.Logger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.concurrent.TimeUnit;


public class PageBase {
    protected RequestSpecification request;
    protected Response response;

    public PageBase(RequestSpecification request, Response response){
        this.request = request;
        this.response = response;
    }
    public boolean isStatusCode(Response response) {
        boolean status = false;
        int code = response.getStatusCode();

        if (code == 200) {
           status = true;
           Logger.printInfo("Status Code is " + code);
        }else
             Logger.printError("Status code is not 200, it was " + code + ". This is the error message: " + response.getBody().prettyPrint());
        return status;
    }
    public boolean isResponseTimeLessThan(Response response, long time) {
        boolean less = false;
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);

        if (responseTime<time) {
            less = true;
            Logger.printInfo("Response Time is less than " + time + "ms. This was the Response Time: " + responseTime + "ms");
        } else
            Logger.printError("Response Time is greater than " + time + "ms. This was the Response Time: " + responseTime + "ms");
        return less;
    }
    public boolean isMutant(Response response){
        boolean mutant = response.jsonPath().getBoolean("result.mutant");

        Logger.printInfo("Mutant is: ".concat(String.valueOf(mutant)).concat(response.jsonPath().prettyPrint()));

        return mutant;
    }
}

