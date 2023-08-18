package com.dimqa.tests.registration;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class RegistrationTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    @DisplayName("Creating a unique user")
    @Description("The test checks for successful registration of a user with a unique email, password and name")
    public void successfulRegistration() {
        User user = UserGenerator.random();
        Response response = client.register(user);
        check.registeredSuccessful(response);
        String accessToken = client.getAccessToken(response);
        response = client.delete(accessToken);
        check.userDeleted(response);
    }

    @Test
    @DisplayName("Creating a user who is already registered")
    @Description("The test checks for a registration error when a user enters an existing email, password, or name")
    public void alreadyRegistered() {
        User user = UserGenerator.alreadyRegistered();
        Response response = client.register(user);
        check.alreadyRegistered(response);
    }

}
