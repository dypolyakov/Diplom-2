package com.dimqa.tests;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.constants.OrderJsons;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderParamTest {
    String json;
    UserClient client = new UserClient();
    UserAssertions check = new UserAssertions();

    public OrderParamTest(String json) {
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
    public void orderWithEmptyIngredientsJson() {
        Response response = client.order(json);
        check.ingredientIdsMustBeProvided(response);
    }
}
