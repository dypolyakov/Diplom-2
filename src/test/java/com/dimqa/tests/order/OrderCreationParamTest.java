package com.dimqa.tests.order;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.constants.OrderJsons;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderCreationParamTest {
    String json;
    UserClient client = new UserClient();
    UserAssertions check = new UserAssertions();

    public OrderCreationParamTest(String json) {
        this.json = json;
    }

    @Parameterized.Parameters
    public static Object[] getJson() {
        return new Object[]{
                OrderJsons.EMPTY_JSON,
                OrderJsons.EMPTY_INGREDIENTS_IDS,
                OrderJsons.NULL_INGREDIENTS_IDS
        };
    }

    @Test
    @DisplayName("Creating an order without ingredients")
    @Description("Test checks that you cannot create an order if the request body contains" +
            "an empty json, an empty ingredient ID, or null instead of an ingredient ID")
    public void orderWithEmptyIngredientsJson() {
        Response response = client.createOrder(json);
        check.ingredientIdsMustBeProvided(response);
    }
}
