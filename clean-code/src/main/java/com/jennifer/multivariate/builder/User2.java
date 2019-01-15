package com.jennifer.multivariate.builder;

/**
 * Created by com.jennifer.huang on 5/17/18.
 */
public class User2 {
    private String firstName;
    private String lastName;
    private int age;
    private String phone;
    private String address;


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


    public static final class User2Builder {  //automatically generation
        private String firstName;
        private String lastName;
        private int age;
        private String phone;
        private String address;

        private User2Builder() {
        }

        public static User2Builder anUser2() {
            return new User2Builder();
        }

        public User2Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public User2Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public User2Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public User2Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User2Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public User2 build() {
            User2 user2 = new User2();
            user2.lastName = this.lastName;
            user2.firstName = this.firstName;
            user2.address = this.address;
            user2.phone = this.phone;
            user2.age = this.age;
            return user2;
        }
    }

    //eg: OkHttpClient, new Retrofit.Builder()
}
