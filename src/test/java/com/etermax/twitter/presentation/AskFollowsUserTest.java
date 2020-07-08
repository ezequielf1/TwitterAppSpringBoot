package com.etermax.twitter.presentation;

import com.etermax.twitter.domain.users.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
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
public class AskFollowsUserTest {

    @Autowired
    private MockMvc mockMvc;

    private final String CREATE_USER_URI = "/user/create";
    private final String FOLLOW_USER_URI = "/user/follow";
    private final String ASK_FOLLOW_URI = "/user/askfollow";
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
    public void whenAskWhoIsAnUserFollowingThenReturnsWhoAreFollowedByTheUser() throws Exception {
        givenFirstUserFollowSecondUser();
        MvcResult mvcResult = whenAskFollowingsOfFirstUser();
        thenGetOKStatus(mvcResult);
        thenGetSecondUserAsFollowed(mvcResult);
    }

    private void givenFirstUserFollowSecondUser() throws Exception {
        createUsers();
        String inputJson = utils.jsonContaining(FIRST_USER.getUsername(), SECOND_USER.getUsername());
        utils.performPatchRequest(mockMvc, FOLLOW_USER_URI, inputJson);
    }

    private void createUsers() throws Exception {
        utils.performPostRequest(mockMvc, CREATE_USER_URI, utils.jsonContaining(FIRST_USER));
        utils.performPostRequest(mockMvc, CREATE_USER_URI, utils.jsonContaining(SECOND_USER));
    }

    private MvcResult whenAskFollowingsOfFirstUser() throws Exception {
        return utils.performGetRequest(mockMvc,
                ASK_FOLLOW_URI,
                utils.jsonContaining(FIRST_USER.getUsername()));
    }

    private void thenGetOKStatus(MvcResult mvcResult) {
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    private void thenGetSecondUserAsFollowed(MvcResult mvcResult) throws Exception {
        JSONArray content = new JSONArray(mvcResult.getResponse().getContentAsString());
        assertEquals(SECOND_USER.getUsername(), content.get(0));
    }
}
