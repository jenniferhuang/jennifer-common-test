package com.jennifer.multivariate.builder;

/**
 * Created by jennifer.huang on 5/17/18.
 */
public class UserWithDefault {
    private final String firstName;     // mandatory
    private final String lastName;      // mandatory
    private final int age;              // optional
    private final String phone;         // optional
    private final String address;       // optional

    private UserWithDefault(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = 1;
            this.phone = "1234154354";
            this.address = "Default address";
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserWithDefault build() {
            return new UserWithDefault(this);
        }
    }
}
