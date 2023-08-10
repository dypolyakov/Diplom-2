package com.dimqa.tests.order;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.Credentials;
import io.restassured.response.Response;
import org.junit.Test;

public class OrderGettingTest {

    UserClient client = new UserClient();
    UserAssertions check = new UserAssertions();

    @Test
    public void getOrderWithoutAuthorization() {
        // Получение заказов без токена авторизации
        Response response = client.getOrders();
        check.shouldBeAuthorised(response);
    }

    @Test
    public void getOrderWithAuthorization() {
        // Авторизация
        Credentials credentials = Credentials.from(UserGenerator.withOrders());
        Response response = client.authorization(credentials);
        check.authorizedSuccessful(response);

        // Получение токена
        String accessToken = client.getAccessToken(response);

        // Получение заказов с токеном авторизации
        response = client.getOrders(accessToken);
        check.successfulReceived(response);
    }
}
