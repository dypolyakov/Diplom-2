package com.dimqa.clients;

import com.dimqa.serialization.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {

    static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    static final String REGISTER_API = "/api/auth/register";

    public Response register(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(REGISTER_API);
    }
}
