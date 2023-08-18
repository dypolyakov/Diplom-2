package com.dimqa.tests.userUpdate;

import com.dimqa.clients.UserAssertions;
import com.dimqa.clients.UserClient;
import com.dimqa.generator.UserGenerator;
import com.dimqa.serialization.User;
import com.dimqa.serialization.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class UserUpdateTest {
    UserClient client = new UserClient();
    UserAssertions check = new UserAssertions();

    @Test
    @DisplayName("Changing user data without authorization")
    @Description("Test checks that it is not possible to change user data without authorization")
    public void withoutAuthorization() {
        UserData userData = UserData.from(UserGenerator.random());
        Response response = client.changeUserData(userData);
        check.shouldBeAuthorised(response);
    }

    @Test
    @DisplayName("Changing user data with authorization")
    @Description("Test verifies that user data is successfully modified if the user is authorized")
    public void withAuthorization() {
        User user = UserGenerator.random();
        Response response = client.register(user);
        check.registeredSuccessful(response);

        String accessToken = client.getAccessToken(response);
        UserData userData = UserData.from(UserGenerator.random());
        response = client.changeUserData(userData, accessToken);
        check.userDataChanged(response, userData);

        response = client.delete(accessToken);
        check.userDeleted(response);
    }

    @Test
    @DisplayName("Changing user data to existing data")
    @Description("Test checks that it is not possible to change user data to existing data")
    public void emailAlreadyExist() {
        User user = UserGenerator.random();
        Response response = client.register(user);
        check.registeredSuccessful(response);

        String accessToken = client.getAccessToken(response);
        UserData userData = UserData.from(UserGenerator.alreadyRegistered());
        response = client.changeUserData(userData, accessToken);
        check.emailAlreadyExist(response);

        response = client.delete(accessToken);
        check.userDeleted(response);
    }
}
