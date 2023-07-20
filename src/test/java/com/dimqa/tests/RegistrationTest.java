package com.dimqa.tests;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.User;
import io.restassured.response.Response;
import org.junit.Test;

public class RegistrationTest {

    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    public void successfulRegistration() {
        User user = UserGenerator.random();
        Response response = client.register(user);
        check.registeredSuccessful(response);
    }

    @Test
    public void alreadyRegistered() {
        User user = UserGenerator.alreadyRegistered();
        Response response = client.register(user);
        check.alreadyRegistered(response);
    }

}
