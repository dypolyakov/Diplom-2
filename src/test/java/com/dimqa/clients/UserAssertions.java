package com.dimqa.clients;

import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;

import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class UserAssertions {
    public void registeredSuccessful(Response response) {
        response.then()
                .statusCode(HTTP_OK)
                .and()
                .assertThat().body("success", equalTo(true));
    }

    public void alreadyRegistered(Response response) {
        response.then()
                .statusCode(HTTP_FORBIDDEN)
                .and()
                .assertThat().body("success", equalTo(false))
                .and()
                .assertThat().body("message", equalTo("User already exists"));
    }

    public void requiredFieldNotFill(Response response) {
        response.then()
                .statusCode(HTTP_FORBIDDEN)
                .and()
                .assertThat().body("success", equalTo(false))
                .and()
                .assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

    public void authorizedSuccessful(Response response) {
        response.then()
                .statusCode(HTTP_OK)
                .and()
                .assertThat()
                .body("success", CoreMatchers.equalTo(true))
                .and()
                .body("accessToken", notNullValue())
                .and()
                .body("refreshToken", notNullValue());
    }
}
