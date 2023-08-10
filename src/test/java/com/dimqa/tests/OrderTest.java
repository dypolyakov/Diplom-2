package com.dimqa.tests;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.constants.OrderJsons;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.Credentials;
import io.restassured.response.Response;
import org.junit.Test;

public class OrderTest {

    UserClient client = new UserClient();
    UserAssertions check = new UserAssertions();

    @Test
    public void orderWithoutAuthorization() {
        // Получить список доступных ингредиентов
        Response response = client.getIngredients();
        check.ingredientsAvailability(response);

        // Создать json со случайными ингредиентами
        String json = client.getRandomIngredients(response);

        // Сделать заказ
        response = client.order(json);
        check.orderSuccessfullyCreated(response);
    }

    @Test
    public void orderWithAuthorization() {
        // Авторизация
        Credentials credentials = Credentials.from(UserGenerator.alreadyRegistered());
        Response response = client.authorization(credentials);
        check.authorizedSuccessful(response);

        // Получение токена
        String accessToken = client.getAccessToken(response);

        // Получить список доступных ингредиентов
        response = client.getIngredients();
        check.ingredientsAvailability(response);

        // Создать json со случайными ингредиентами
        String json = client.getRandomIngredients(response);

        // Сделать заказ авторизованным пользователем
        response = client.order(json, accessToken);
        check.orderSuccessfullyCreatedWithAuth(response);
    }

    @Test
    public void orderWithEmptyIngredientsJson() {
        String json = OrderJsons.EMPTY_JSON;
        Response response = client.order(json);
        check.ingredientIdsMustBeProvided(response);
    }

    @Test
    public void orderWithoutIngredientsIDs() {
        String json = OrderJsons.EMPTY_INGREDIENTS_IDS;
        Response response = client.order(json);
        check.ingredientIdsMustBeProvided(response);
    }

    @Test
    public void orderWithNullIngredientsIDs() {
        String json = OrderJsons.NULL_INGREDIENTS_IDS;
        Response response = client.order(json);
        check.ingredientIdsMustBeProvided(response);
    }

    @Test
    public void orderWithInvalidIngredientsIDs() {
        String json = OrderJsons.INVALID_INGREDIENTS_IDS;
        Response response = client.order(json);
        check.internalServerError(response);
    }
}


