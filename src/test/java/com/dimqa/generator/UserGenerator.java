package com.dimqa.generator;

import com.dimqa.serialization.User;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public User alreadyRegistered() {
        return new User("already@registered.ru", "already", "registered");
    }

    public User random() {
        String email = RandomStringUtils.randomAlphabetic(5, 8) + "@" + RandomStringUtils.randomAlphabetic(4, 6) + ".com";
        String password = RandomStringUtils.randomAlphanumeric(8, 12);
        String name = RandomStringUtils.randomAlphabetic(4, 10);
        return new User(email, password, name);
    }
}
