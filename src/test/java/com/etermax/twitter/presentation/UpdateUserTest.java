package com.etermax.twitter.presentation;

import com.etermax.twitter.domain.users.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.etermax.twitter.domain.users.UserBuilder.aUser;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateUserTest {

    @Autowired
    private MockMvc mockMvc;

    private final String UPDATE_USER_URI = "/user/update";
    private final String CREATE_USER_URI = "/user/create";
    private final Utils utils = new Utils();
    private final String USERNAME = "Overterror";
    private final String REAL_NAME = "Ezequiel";
    private final String UPDATED_REAL_NAME = "Brian";
    private final User UPDATED_USER = aUser().withUsername(USERNAME).withRealName(UPDATED_REAL_NAME).build();
    private final User USER = aUser().withUsername(USERNAME).withRealName(REAL_NAME).build();

    @Test
    public void whenUserIsUpdatedThenReturnsNoContent() throws Exception {
        createUser();
        String inputJson = generateUpdatedUserInputString();

        MvcResult mvcResult = utils.performPutRequest(mockMvc, UPDATE_USER_URI, inputJson);

        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenUpdateUserNotRegisteredThenReturnsUsernameNotRegistered() throws Exception {
        String inputJson = generateUpdatedUserInputString();

        MvcResult mvcResult = utils.performPutRequest(mockMvc, UPDATE_USER_URI, inputJson);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResult.getResponse().getStatus());
        assertEquals("Username not registered",
                utils.createJsonFrom(mvcResult.getResponse().getContentAsString()).get("message"));
    }

    private String generateUpdatedUserInputString() throws Exception {
        return utils.jsonContaining(UPDATED_USER);
    }

    private void createUser() throws Exception {
        utils.performPostRequest(mockMvc, CREATE_USER_URI, utils.jsonContaining(USER));
    }
}
