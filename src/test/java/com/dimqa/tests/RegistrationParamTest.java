package com.dimqa.tests;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.User;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class RegistrationParamTest {

    private final User user;
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    public RegistrationParamTest(User user) {
        this.user = user;
    }

    @Parameterized.Parameters
    public static Object[] getUser() {
        return new Object[] {
                UserGenerator.withoutEmail(),
                UserGenerator.emptyEmail(),
                UserGenerator.nullEmail(),
                UserGenerator.withoutPassword(),
                UserGenerator.emptyPassword(),
                UserGenerator.nullPassword(),
                UserGenerator.withoutName(),
                UserGenerator.emptyName(),
                UserGenerator.nullName()
        };
    }

    @Test
    public void withoutRequiredField() {
        Response response = client.register(user);
        check.requiredFieldNotFill(response);
    }

}
