package com.etermax.twitter.presentation;

import com.etermax.twitter.domain.users.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


class Utils {
    protected String jsonContaining(User user) throws JSONException {
        return new JSONObject()
                .put("realName", user.getRealName())
                .put("username", user.getUsername())
                .toString();
    }

    protected JSONObject createJsonFrom(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }

    protected MvcResult performPostRequest(MockMvc mockMvc, String uri, String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }

    protected MvcResult performPutRequest(MockMvc mockMvc, String uri, String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }

    protected MvcResult performPatchRequest(MockMvc mockMvc, String uri, String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.patch(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }
}