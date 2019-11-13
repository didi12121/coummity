package com.example.didi1309.coummity.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.didi1309.coummity.dto.AccessTokenDTO;
import com.example.didi1309.coummity.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitbubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO)  {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            System.out.println("String:"+string);
            return string;
        }catch (IOException e){
            e.printStackTrace();
        }
            return null;
    }
    public GithubUser getUser(String accessToken){
        String url="https://api.github.com/user?"+accessToken;
        System.out.println("url:-----"+url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return  githubUser;
        } catch (IOException e) {
           return null;
        }

    }
}
