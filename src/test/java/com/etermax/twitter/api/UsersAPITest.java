package com.etermax.twitter.api;

import com.etermax.twitter.domain.users.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
class UsersAPITest {

    @Autowired
    private MockMvc mockMvc;

    private final String createUserUri = "/user/create";

    @Test
    public void createUser() throws Exception {
        User user = new User("Ezequiel", "Overterror");
        String inputJson = jsonContaining(user);
        MvcResult mvcResult = performCreateUserRequest(inputJson);
        assertEquals(200, mvcResult.getResponse().getStatus());
        JSONAssert.assertEquals(jsonContaining(user), mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    public void createUser_returnUserWithThatUsernameAlreadyExists() throws Exception {
        User user = new User("Ezequiel", "Overterror");
        String inputJson = jsonContaining(user);

        MvcResult mvcResultFirstCreation = performCreateUserRequest(inputJson);
        MvcResult mvcResultSecondCreation = performCreateUserRequest(inputJson);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), mvcResultSecondCreation.getResponse().getStatus());
        assertEquals("Username already in use",
                createJsonFrom(mvcResultSecondCreation.getResponse().getContentAsString()).get("message"));
    }

    private String jsonContaining(User user) throws JSONException {
        return new JSONObject()
                .put("realName", user.getRealName())
                .put("username", user.getUsername())
                .toString();
    }

    private JSONObject createJsonFrom(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }

    private MvcResult performCreateUserRequest(String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(createUserUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }
}