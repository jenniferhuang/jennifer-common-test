package com.jennifer.multivariate.builder;

/**
 * Created by com.jennifer.huang on 6/7/18.
 */
public class TestUser {


    User user = new User.UserBuilder("firstName", "lastName").build();

    User2 user2 = User2.User2Builder.anUser2().withFirstName("firstName2").withLastName("lastName2").build();

    UserWithDefault userWithDefault = new UserWithDefault.UserBuilder("firstName3","lastName3").build();



}
