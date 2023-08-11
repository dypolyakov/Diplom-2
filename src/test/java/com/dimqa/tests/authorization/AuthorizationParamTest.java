package com.dimqa.tests.authorization;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.Credentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AuthorizationParamTest {
    Credentials credentials;
    UserClient client = new UserClient();
    UserAssertions check = new UserAssertions();

    public AuthorizationParamTest(Credentials credentials) {
        this.credentials = credentials;
    }

    @Parameterized.Parameters
    public static Object[] getUser() {
        return new Object[]{
                Credentials.from(UserGenerator.withoutEmail()),
                Credentials.from(UserGenerator.emptyEmail()),
                Credentials.from(UserGenerator.nullEmail()),
                Credentials.from(UserGenerator.withoutPassword()),
                Credentials.from(UserGenerator.emptyPassword()),
                Credentials.from(UserGenerator.nullPassword()),
                Credentials.from(UserGenerator.withoutName()),
                Credentials.from(UserGenerator.emptyName()),
                Credentials.from(UserGenerator.nullName())
        };
    }

    @Test
    @DisplayName("Login with wrong login and password")
    @Description("The test checks for an authorization error when a user enters an incorrect email, password or name")
    public void failedAuthorization() {
        Response response = client.authorization(credentials);
        check.authorizationFailed(response);
    }
}
