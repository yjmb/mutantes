package com.mercadolibre.mutantes.pages;

import com.mercadolibre.mutantes.utils.JSonUtil;
import com.mercadolibre.mutantes.utils.Logger;
import com.mercadolibre.mutantes.utils.RequestHeaders;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MutantPage extends PageBase {
    public MutantPage(RequestSpecification request, Response response) {
        super(request, response);
    }

    public Response postMutant() {
        JSONObject jsonObject = JSonUtil.readAndValidateJSON();
        if (jsonObject != null) {
            baseURI = "http://localhost:8080/mutant/";
            request = given().headers(RequestHeaders.getRequestHeaders());
            request.body(jsonObject.toJSONString());
            response = request.post();
        }
        return response;
    }

    public boolean isSchemaValid(Response response) {
        boolean valid = false;

        try {
            response.then().body(matchesJsonSchemaInClasspath("mutantSchema.json"));
            valid = true;
            Logger.printInfo("Schema is valid");
        } catch (Exception e) {
            Logger.printError("Schema is invalid due to: ".concat(e.getMessage()));
        }
        return valid;

    }


}

