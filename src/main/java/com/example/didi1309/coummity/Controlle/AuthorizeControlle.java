package com.example.didi1309.coummity.Controlle;

import com.example.didi1309.coummity.dto.AccessTokenDTO;
import com.example.didi1309.coummity.dto.GithubUser;
import com.example.didi1309.coummity.mapper.UserMapper;
import com.example.didi1309.coummity.model.User;
import com.example.didi1309.coummity.provider.GitbubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeControlle {

    @Autowired
    private GitbubProvider gitbubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String secret;

    @Value("${github.redirct.uri}")
    private String uri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request)  {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(uri);
        String accessToken = gitbubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = gitbubProvider.getUser(accessToken);
        if (githubUser!=null){
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccoundId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtModified());
            userMapper.insert(user);
            request.getSession().setAttribute("User",githubUser);
            return "redirect:/";
        }else {
            return "redirect:index";
        }
    }
}
