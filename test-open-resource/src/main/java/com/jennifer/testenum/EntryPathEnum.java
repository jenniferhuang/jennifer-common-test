package com.jennifer.testenum;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 11/3/2017
 */
public enum EntryPathEnum {
    AuthorizationCode("Authorization Code"),
    ClientCredentialsByAccount("Client Credentials By Account"),
    ClientCredentialsByBrand("Client Credentials By Brand"),
    LoginHash("Login Hash"),
    ROPC("Password flow"),
    RefreshToken("Refresh Access Token");

    private final String displayName;

    EntryPathEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
