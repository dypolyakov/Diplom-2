package com.dimqa.clients;

import com.dimqa.serialization.UserData;
import io.restassured.response.Response;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

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

    public void shouldBeAuthorised(Response response) {
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

    public void emailAlreadyExist(Response response) {
        response.then()
                .statusCode(HTTP_FORBIDDEN)
                .and()
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("User with such email already exists"));
    }

    public void ingredientsAvailability(Response response) {
        response.then()
                .statusCode(HTTP_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true))
                .and()
                .body("data", notNullValue());
    }

    public void orderSuccessfullyCreated(Response response) {
        response.then()
                .statusCode(HTTP_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true))
                .and()
                .body("name", notNullValue())
                .and()
                .body("order.number", greaterThan(0));
    }

    public void orderSuccessfullyCreatedWithAuth(Response response) {
        response.then()
                .statusCode(HTTP_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order.ingredients", notNullValue())
                .body("order._id", notNullValue())
                .body("order.owner", notNullValue())
                .body("order.status", equalTo("done"))
                .body("order.number", greaterThan(0))
                .body("order.price", greaterThan(0));

    }

    public void ingredientIdsMustBeProvided(Response response) {
        response.then()
                .statusCode(HTTP_BAD_REQUEST)
                .and()
                .assertThat()
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    public void internalServerError(Response response) {
        response.then()
                .statusCode(HTTP_INTERNAL_ERROR);
    }

    public void successfulReceived(Response response) {
        response.then()
                .statusCode(HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("orders", notNullValue())
                .body("total", greaterThan(0));

    }

    public void userDeleted(Response response) {
        response.then()
                .statusCode(HTTP_ACCEPTED)
                .and()
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
    }
}
