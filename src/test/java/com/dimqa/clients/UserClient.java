package com.dimqa.clients;

import com.dimqa.constants.Api;
import com.dimqa.generator.IngredientGenerator;
import com.dimqa.serialization.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class UserClient {

    public Response register(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Api.BASE_URI)
                .body(user)
                .when()
                .post(Api.REGISTER_API);
    }

    public Response authorization(Credentials credentials) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Api.BASE_URI)
                .body(credentials)
                .when()
                .post(Api.AUTH_API);
    }


    public String getAccessToken(Response response) {
        return response.then()
                .extract()
                .path("accessToken");
    }

    public Response changeUserData(UserData userData) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Api.BASE_URI)
                .body(userData)
                .when()
                .patch(Api.USER_API);
    }

    public Response changeUserData(UserData userData, String token) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .baseUri(Api.BASE_URI)
                .body(userData)
                .when()
                .patch(Api.USER_API);
    }

    public Response getIngredients() {
        return given()
                .baseUri(Api.BASE_URI)
                .when()
                .get(Api.INGREDIENTS);
    }

    public IngredientData getIngredientsList(Response response) {
        return response
                .body()
                .as(IngredientData.class);
    }

    public Response createOrder(String json) {
        return  given()
                .contentType(ContentType.JSON)
                .body(json)
                .baseUri(Api.BASE_URI)
                .post(Api.ORDERS);
    }

    public Response createOrder(String json, String token) {
        return  given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(json)
                .baseUri(Api.BASE_URI)
                .post(Api.ORDERS);
    }

    public String getRandomIngredients(Response response) {
        ArrayList<Ingredient> ingredients = getIngredientsList(response).getData();
        IngredientGenerator ingredientGenerator = new IngredientGenerator(ingredients);
        JsonArray ingredientsList = ingredientGenerator.random();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("ingredients", ingredientsList);
        Gson gson = new Gson();
        return gson.toJson(jsonObject);
    }

    public Response getOrders() {
        return given()
                .baseUri(Api.BASE_URI)
                .get(Api.ORDERS);
    }
    public Response getOrders(String token) {
        return given()
                .header("Authorization", token)
                .baseUri(Api.BASE_URI)
                .get(Api.ORDERS);
    }
}
