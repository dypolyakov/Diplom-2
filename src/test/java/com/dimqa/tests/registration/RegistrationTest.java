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
    @Description("User registration with a random username, password and name")
    public void successfulRegistration() {
        User user = UserGenerator.random();
        Response response = client.register(user);
        check.registeredSuccessful(response);
    }

    @Test
    @DisplayName("Creating a user who is already registered")
    @Description("Registering a user with a username and password that already exists")
    public void alreadyRegistered() {
        User user = UserGenerator.alreadyRegistered();
        Response response = client.register(user);
        check.alreadyRegistered(response);
    }

}
