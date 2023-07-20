package com.dimqa.tests;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.Credentials;
import io.restassured.response.Response;
import org.junit.Test;

public class AuthorizationTest {
    UserClient client = new UserClient();
    UserAssertions check = new UserAssertions();

    @Test
    public void successfulAuthorization() {
        Credentials credentials = Credentials.from(UserGenerator.alreadyRegistered());
        Response response = client.authorization(credentials);
        check.registeredSuccessful(response);
    }
}
