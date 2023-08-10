package com.dimqa.tests;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.User;
import com.dimqa.serialization.UserData;
import io.restassured.response.Response;
import org.junit.Test;

public class UserDataChangingTest {
    UserClient client = new UserClient();
    UserAssertions check = new UserAssertions();

    @Test
    public void withoutAuthorization() {
        UserData userData = UserData.from(UserGenerator.random());
        Response response = client.changeUserData(userData);
        check.shouldBeAuthorised(response);
    }

    @Test
    public void withAuthorization() {
        User user = UserGenerator.random();
        Response response = client.register(user);
        check.registeredSuccessful(response);
        String accessToken = client.getAccessToken(response);

        UserData userData = UserData.from(UserGenerator.random());

        response = client.changeUserData(userData, accessToken);
        check.userDataChanged(response, userData);
    }

    @Test
    public void emailAlreadyExist() {
        User user = UserGenerator.random();
        Response response = client.register(user);
        check.registeredSuccessful(response);
        String accessToken = client.getAccessToken(response);
        UserData userData = UserData.from(UserGenerator.alreadyRegistered());
        response = client.changeUserData(userData, accessToken);
        check.emailAlreadyExist(response);
    }
}
