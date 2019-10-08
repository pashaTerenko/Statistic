package terenko.statistic.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import terenko.statistic.DTO.PostDTO;
import terenko.statistic.DTO.UserDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class API {
    @Autowired
    PostDTO post;

    List<UserDTO> likersList;
    List<UserDTO> RepostersList;
    List<UserDTO> ComentatorsList;
    List<UserDTO> MentorsList;


    private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImRiYTYzMGE0YzIxYWZlNzRhYTVlNTgwZjBiZjliMDQ1YThmYzY4NWViMjE0ZmFmZTY4ZDAzMGQzZjdiNThhMDg3M2M1MzU3MTNjNjIxNmE5In0.eyJhdWQiOiIyIiwianRpIjoiZGJhNjMwYTRjMjFhZmU3NGFhNWU1ODBmMGJmOWIwNDVhOGZjNjg1ZWIyMTRmYWZlNjhkMDMwZDNmN2I1OGEwODczYzUzNTcxM2M2MjE2YTkiLCJpYXQiOjE1Njg2MzI3MDEsIm5iZiI6MTU2ODYzMjcwMSwiZXhwIjoxNjAwMjU1MTAxLCJzdWIiOiIzMDQ2MTAiLCJzY29wZXMiOltdfQ.BB_dmBJDg-NO72-HSqudwt3Ql4kO7uyVjx4sMTpMURpJZfRpd1-7wUjfERWumhp1JcQzFwiRsihKTLN_rVeLNzspcXUHtp6OTRBEPowrzhQq3tB8-mQxTL-8KV2QPOaDmYufoowtCaxbTp7ciKs7Ybs4t9XYfGQoyBurWJsspOU_ikvWHlZViHZQjs82aAIblC-XyQh94sJz0_3mDHQPcGhlpBXp-RMi-hghGZsFS_ZhugSKvz5bKeR0bzDTui1baUoGnCFOWwSDn_tek1rSpAIdOe5WK1J2Opvjx7kjq7ycjbDy8ZNurWhEExa8rrFRRHlzN2AwiWkwAd6BmMHI_nVhgGbqUzDg88_393nX8T8Fdrek-IU1k461MzAycsRiKTj_vRXVyNie25v-wg3aQ9PciB-zQH1JbBM6wPmavwi-5zWD5Ab5m3FGijBviPCnN2Q-H9aBN3zPLYrYHG305Rmq6DHc_QoC_GYISk_BbbU3_DrvnUml2ey07B0bprVKJPZWYE9LHW5A3k4-yV3eAR5rf-JcQjCigONs0lkcA9uu91R-n3VxmtIyOIO76SLdkiZEebV8yvIC696zrxPMzNktoDUc-SP359MZDHIvmtL2uyy3s83n3LtgwgamZC4e6_-GmU89npACLD5hK7-lVXeVRGAKZuhTHKWuKbEqLJ8";

    public API() {
    }

    public void pushPost(String slug) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rep = restTemplate.postForEntity("https://api.inrating.top/v1/users/posts/get?slug=" + slug, getRequest(), String.class);
        GsonBuilder gsonB=new GsonBuilder();
        Gson gson =gsonB.create();
         post = gson.fromJson(rep.getBody(), PostDTO.class);



        getLikers();
        getReposters();
        getComentators();
        getMentions();
        post.setMentions_count(Integer.toString(MentorsList.size()));
    }

    private HttpEntity<String> getRequest() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> request = new HttpEntity<>("body", headers);
        return request;
    }

    public void getLikers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rel = restTemplate.postForEntity("https://api.inrating.top/v1/users/posts/likers/all?id=" + post.getId(), getRequest(), String.class);

        try {
            likersList = parseUsers(new JSONObject(rel.getBody()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getReposters() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rel = restTemplate.postForEntity("https://api.inrating.top/v1/users/posts/reposters/all?id=" + post.getId(), getRequest(), String.class);

        try {
            RepostersList = parseUsers(new JSONObject(rel.getBody()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getComentators() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rel = restTemplate.postForEntity("https://api.inrating.top/v1/users/posts/commentators/all?id=" + post.getId(), getRequest(), String.class);

        try {
            ComentatorsList = parseUsers(new JSONObject(rel.getBody()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getMentions() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rel = restTemplate.postForEntity("https://api.inrating.top/v1/users/posts/mentions/all?id=" + post.getId(), getRequest(), String.class);

        try {
            MentorsList = parseUsers(new JSONObject(rel.getBody()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public List<UserDTO> parseUsers(JSONObject json) throws JSONException {
        List<UserDTO> users = new ArrayList<>();
        JSONArray userJSON = json.getJSONArray("data");

        for (int i = 0; i < userJSON.length(); i++) {
            String id = userJSON.getJSONObject(i).getString("id");
            String name = userJSON.getJSONObject(i).getString("name");
            String lastname = userJSON.getJSONObject(i).getString("lastname");
            String nickname = userJSON.getJSONObject(i).getString("nickname");
            String avatarURL = userJSON.getJSONObject(i).getJSONObject("avatar_image").getString("url_small");
            users.add(new UserDTO(id, nickname, name, lastname, avatarURL));
        }

        return users;
    }

    public List<UserDTO> getLikersList() {
        return likersList;
    }

    public List<UserDTO> getRepostersList() {
        return RepostersList;
    }

    public List<UserDTO> getComentatorsList() {
        return ComentatorsList;
    }

    public List<UserDTO> getMentorsList() {
        return MentorsList;
    }

    public PostDTO getPost() {
        return post;
    }
}
