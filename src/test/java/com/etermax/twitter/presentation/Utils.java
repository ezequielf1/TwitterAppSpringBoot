package com.etermax.twitter.presentation;

import com.etermax.twitter.domain.users.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


class Utils {
    public String jsonContaining(User user) throws JSONException {
        return new JSONObject()
                .put("realName", user.getRealName())
                .put("username", user.getUsername())
                .toString();
    }

    public String jsonContaining(String followerUsername, String followedUsername) throws JSONException {
        return new JSONObject()
                .put("followerUsername", followerUsername)
                .put("followedUsername", followedUsername)
                .toString();
    }

    public String jsonContaining(String username) throws JSONException {
        return new JSONObject()
                .put("username", username)
                .toString();
    }

    public JSONObject createJsonFrom(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }

    public MvcResult performPostRequest(MockMvc mockMvc, String uri, String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }

    public MvcResult performPutRequest(MockMvc mockMvc, String uri, String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }

    public MvcResult performPatchRequest(MockMvc mockMvc, String uri, String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.patch(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }

    public MvcResult performGetRequest(MockMvc mockMvc, String uri, String inputJson) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
    }
}