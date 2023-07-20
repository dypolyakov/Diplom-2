package com.dimqa.clients;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserAssertions {
    public void registeredSuccessful(Response response) {
        response
                .then()
                .statusCode(200)
                .and()
                .assertThat().body("success", is(true));
    }

    public void alreadyRegistered(Response response) {
        response
                .then()
                .statusCode(403)
                .and()
                .assertThat().body("success", is(false))
                .and()
                .assertThat().body("message", equalTo("User already exists"));
    }
}
