package com.dimqa.clients;

import com.dimqa.serialization.Credentials;
import com.dimqa.serialization.UserData;
import io.restassured.response.Response;

import static java.net.HttpURLConnection.*;
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
                .body("success", equalTo(true))
                .and()
                .body("accessToken", notNullValue())
                .and()
                .body("refreshToken", notNullValue());
    }

    public void authorizationFailed(Response response) {
        response.then()
                .statusCode(HTTP_UNAUTHORIZED)
                .and()
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }

    public void withoutAutrohization(Response response) {
        response.then()
                .statusCode(HTTP_UNAUTHORIZED)
                .and()
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }

    public void userDataChanged(Response response, UserData userData) {
        response.then()
                .statusCode(HTTP_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true))
                .and()
                .body("user.email", equalTo(userData.getEmail().toLowerCase()))
                .and()
                .body("user.name", equalTo(userData.getName()));
    }

    public void emailAlreadyExist(Response response, UserData userData) {
        response.then()
                .statusCode(HTTP_FORBIDDEN)
                .and()
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("User with such email already exists"));
    }
}
