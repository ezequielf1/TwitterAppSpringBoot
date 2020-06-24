package com.etermax.twitter.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class UsersAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/create")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"1\", \"realname\": \"Ezequiel\", \"username\": \"overterror\"}"))
                .andExpect(status().isOk());
    }

    private String jsonContaining() throws JSONException {
        return new JSONObject()
                .put("id", "1")
                .put("realname", "Ezequiel")
                .put("username", "overterror")
                .toString();
    }
}