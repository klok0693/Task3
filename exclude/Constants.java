package com.company.company.config.security;

public class Constants {

    static final String CLIEN_ID = "devglan-client",
            CLIENT_SECRET = "devglan-secret",
            GRANT_TYPE_PASSWORD = "password",
            AUTHORIZATION_CODE = "authorization_code",
            REFRESH_TOKEN = "refresh_token",
            IMPLICIT = "implicit",
            SCOPE_READ = "read",
            SCOPE_WRITE = "write",
            TRUST = "trust";
    static final int    ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60,
            FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
}
