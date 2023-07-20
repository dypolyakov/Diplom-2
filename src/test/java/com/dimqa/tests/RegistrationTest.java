package com.dimqa.tests;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.User;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

//@RunWith(Parameterized.class)
public class RegistrationTest {


    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    public void successfulRegistration() {
        User user = generator.random();
        Response response = client.register(user);
        check.registeredSuccessful(response);
    }

    @Test
    public void alreadyRegistered() {
        User user = generator.alreadyRegistered();
        Response response = client.register(user);
        check.alreadyRegistered(response);
    }

    @Test
    public void emptyEmail() {
        User user = new User("", "password", "name");
        Response response = client.register(user);
        response
                .then()
                .statusCode(403)
                .and()
                .assertThat().body("success", is(false))
                .and()
                .assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

}
