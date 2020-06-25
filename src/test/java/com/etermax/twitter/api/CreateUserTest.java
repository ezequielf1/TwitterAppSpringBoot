package com.etermax.twitter.api;

import com.etermax.twitter.domain.users.User;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;

public class CreateUserTest extends UsersAPITest {
    @Test
    public void createUser() throws Exception {
        User user = new User(USERNAME, REAL_NAME);
        String inputJson = jsonContaining(user);

        MvcResult mvcResult = performCreateUserRequest(inputJson);

        assertEquals(200, mvcResult.getResponse().getStatus());
        JSONAssert.assertEquals(inputJson, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    public void createUser_returnUsernameAlreadyExists() throws Exception {
        User user = new User(USERNAME, REAL_NAME);
        String inputJson = jsonContaining(user);

        MvcResult mvcResultFirstCreation = performCreateUserRequest(inputJson);
        MvcResult mvcResultSecondCreation = performCreateUserRequest(inputJson);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResultSecondCreation.getResponse().getStatus());
        assertEquals("Username already in use",
                createJsonFrom(mvcResultSecondCreation.getResponse().getContentAsString()).get("message"));
    }
}
