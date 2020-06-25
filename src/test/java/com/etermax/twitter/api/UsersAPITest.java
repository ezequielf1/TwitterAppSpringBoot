package com.etermax.twitter.api;

import com.etermax.twitter.domain.users.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
class UsersAPITest {

    @Autowired
    protected MockMvc mockMvc;

    private final String createUserUri = "/user/create";
    private final String updateUserUri = "/user/update";
    protected final String USERNAME = "Overterror";
    protected final String REAL_NAME = "Ezequiel";
    protected final String UPDATED_REAL_NAME = "Brian";

    protected String jsonContaining(User user) throws JSONException {
        return new JSONObject()
                .put("realName", user.getRealName())
                .put("username", user.getUsername())
                .toString();
    }

    protected JSONObject createJsonFrom(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }

    protected MvcResult performCreateUserRequest(String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(createUserUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }

    protected MvcResult performUpdateUserRequest(String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.put(updateUserUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();
    }
}