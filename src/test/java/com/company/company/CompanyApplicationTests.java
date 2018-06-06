package com.company.company;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@NotNullByDefault

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompanyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyApplicationTests {


    private JwtTokenStore tokenStore = new JwtTokenStore(accessTokenConverter());

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Test
    public void whenTokenDoesNotContainIssuer_thenSuccess() {
        String tokenValue = obtainAccessToken("1", "Alex123", "123");
        OAuth2Authentication auth = tokenStore.readAuthentication(tokenValue);
        Map<String, Object> details = (Map<String, Object>) auth.getDetails();

        assertTrue(details.containsKey("organization"));
    }

    private String obtainAccessToken(
            String clientId, String username, String password) {

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("id", clientId);
        params.put("username", username);
        params.put("password", password);
        Response response = RestAssured.given()
                .auth().preemptive().basic(clientId, "secret")
                .and().with().params(params).when()
                .post("http://localhost:8090/spring-security-oauth-server/oauth/token");
        return response.jsonPath().getString("access_token");
    }
}
