package com.dimqa;

import com.dimqa.Serialization.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RegistrationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }
    @Test
    public void successfulRegistration() {
        User user = new User("34test-data@yandex.ru", "password", "Username");
        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .and()
                .assertThat().body("success", is(true));
    }

    @Test
    public void alreadyRegistered() {
        User user = new User("already@registered.ru", "already", "registered");
        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(403)
                .and()
                .assertThat().body("success", is(false))
                .and()
                .assertThat().body("message", equalTo("User already exists"));
    }

    @Test
    public void emptyEmail() {
        User user = new User("", "password", "name");
        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(403)
                .and()
                .assertThat().body("success", is(false))
                .and()
                .assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

}
