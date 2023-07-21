package com.dimqa.clients;

import com.dimqa.serialization.Credentials;
import com.dimqa.serialization.User;
import com.dimqa.serialization.UserData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {

    static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    static final String REGISTER_API = "/api/auth/register";
    static final String AUTH_API = "/api/auth/login";
    static final String USER_API = "/api/auth/user";

    public Response register(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(REGISTER_API);
    }

    public Response authorization(Credentials credentials) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(credentials)
                .when()
                .post(AUTH_API);
    }


    public String getAccessToken(Response response) {
        return response.then()
                .extract()
                .path("accessToken");
    }

    public Response changeUserDataWithoutAuth(UserData userData) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(userData)
                .when()
                .patch(USER_API);
    }

    public Response changeUserData(UserData userData, String token) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(userData)
                .when()
                .patch(USER_API);
    }
}
