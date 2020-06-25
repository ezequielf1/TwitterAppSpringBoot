package com.etermax.twitter.api;

import com.etermax.twitter.domain.users.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;

public class UpdateUserTest extends UsersAPITest {

    @Test
    public void updateUserRealName() throws Exception {
        performCreateUserRequest(jsonContaining(new User(USERNAME, REAL_NAME)));
        User updatedUser = new User(USERNAME, UPDATED_REAL_NAME);
        String inputJson = jsonContaining(updatedUser);

        MvcResult mvcResult = performUpdateUserRequest(inputJson);

        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void updateUserRealName_UserNotExists() throws Exception {
        User updatedUser = new User(USERNAME, REAL_NAME);
        String inputJson = jsonContaining(updatedUser);

        MvcResult mvcResult = performUpdateUserRequest(inputJson);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
        assertEquals("Username not registered",
                createJsonFrom(mvcResult.getResponse().getContentAsString()).get("message"));
    }
}
