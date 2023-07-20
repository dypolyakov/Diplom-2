package com.dimqa.generator;

import com.dimqa.serialization.User;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User alreadyRegistered() {
        return new User("already@registered.ru", "already", "registered");
    }

    public static User random() {
        String email = getRandomEmail();
        String password = getRandomPassword();
        String name = getRandomName();
        return new User(email, password, name);
    }

    public static User withoutEmail() {
        return new User().setName(getRandomName()).setPassword(getRandomPassword());
    }

    public static User emptyEmail() {
        return new User().setEmail("").setName(getRandomName()).setPassword(getRandomPassword());
    }

    public static User nullEmail() {
        return new User().setEmail(null).setName(getRandomName()).setPassword(getRandomPassword());
    }

    public static User withoutName() {
        return new User().setEmail(getRandomEmail()).setPassword(getRandomPassword());
    }

    public static User emptyName() {
        return new User().setEmail(getRandomEmail()).setName("").setPassword(getRandomPassword());
    }

    public static User nullName() {
        return new User().setEmail(getRandomEmail()).setName(null).setPassword(getRandomPassword());
    }

    public static User withoutPassword() {
        return new User().setEmail(getRandomEmail()).setName(getRandomName());
    }

    public static User emptyPassword() {
        return new User().setEmail(getRandomEmail()).setName(getRandomName()).setPassword("");
    }

    public static User nullPassword() {
        return new User().setEmail(getRandomEmail()).setName(getRandomName()).setPassword(null);
    }

    private static String getRandomName() {
        return RandomStringUtils.randomAlphabetic(4, 10);
    }

    public static String getRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(8, 12);
    }

    public static String getRandomEmail() {
        return RandomStringUtils.randomAlphabetic(5, 8) + "@" + RandomStringUtils.randomAlphabetic(4, 6) + ".com";
    }
}
