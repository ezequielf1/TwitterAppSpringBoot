package com.etermax.twitter.presentation;

import com.etermax.twitter.domain.users.User;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.etermax.twitter.domain.users.UserBuilder.aUser;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class CreateUserTest {

    @Autowired
    private MockMvc mockMvc;

    private final String createUserUri = "/user/create";
    private Utils utils = new Utils();
    private final String USERNAME = "Overterror";
    private final String REAL_NAME = "Ezequiel";
    private final String USERNAME_ALREADY_IN_USE = "Username already in use";
    private final User USER = aUser().withUsername(USERNAME).withRealName(REAL_NAME).build();

    @Test
    public void whenUserIsCreatedThenReturnsTheNewUser() throws Exception {
        String inputJson = generateUserInputString();

        MvcResult mvcResult = utils.performPostRequest(mockMvc, createUserUri, inputJson);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        JSONAssert.assertEquals(inputJson, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    @DirtiesContext
    public void whenCreatesAnUserThatAlreadyExistsThenReturnsUsernameAlreadyInUse() throws Exception {
        String inputJson = generateUserInputString();

        MvcResult mvcResultFirstCreation = utils.performPostRequest(mockMvc, createUserUri, inputJson);
        MvcResult mvcResultSecondCreation = utils.performPostRequest(mockMvc, createUserUri, inputJson);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResultSecondCreation.getResponse().getStatus());
        assertEquals(USERNAME_ALREADY_IN_USE,
                utils.createJsonFrom(mvcResultSecondCreation.getResponse().getContentAsString()).get("message"));
    }

    private String generateUserInputString() throws Exception {
        return utils.jsonContaining(USER);
    }
}
