package com.etermax.twitter.presentation;

import com.etermax.twitter.domain.users.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static com.etermax.twitter.domain.users.UserBuilder.aUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class FollowUserTest {
    @Autowired
    private MockMvc mockMvc;

    private final String CREATE_USER_URI = "/user/create";
    private final String FOLLOW_USER_URI = "/user/follow";
    private final Utils utils = new Utils();
    private final User FIRST_USER = aUser()
            .withUsername("Overterror")
            .withRealName("Ezequiel")
            .build();
    private final User SECOND_USER = aUser()
            .withUsername("Stoolgear")
            .withRealName("Brian")
            .build();

    @Test
    public void whenUserFollowsOtherUserThenReturnsUserFollowingsUpdated() throws Exception {
        createUsers();
        String inputJson = utils.jsonContaining(FIRST_USER.getUsername(), SECOND_USER.getUsername());

        MvcResult mvcResult = utils.performPatchRequest(mockMvc, FOLLOW_USER_URI, inputJson);

        thenGetOKStatusAndSecondUserAsFollowed(mvcResult);
    }

    private String createFollowJsonRequest() throws JSONException {
        return new JSONObject()
                .put("followerUsername", FIRST_USER.getUsername())
                .put("followedUsername", SECOND_USER.getUsername())
                .toString();
    }

    private void createUsers() throws Exception {
        utils.performPostRequest(mockMvc, CREATE_USER_URI, utils.jsonContaining(FIRST_USER));
        utils.performPostRequest(mockMvc, CREATE_USER_URI, utils.jsonContaining(SECOND_USER));
    }

    private void thenGetOKStatusAndSecondUserAsFollowed(MvcResult mvcResult) throws Exception {
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        JSONObject jsonContent = utils.createJsonFrom(mvcResult.getResponse().getContentAsString());
        JSONArray followings = jsonContent.getJSONArray("followings");
        assertEquals(SECOND_USER.getUsername(), followings.get(0));
    }
}