package com.example.didi1309.coummity.Controlle;

import com.example.didi1309.coummity.dto.AccessTokenDTO;
import com.example.didi1309.coummity.dto.GithubUser;
import com.example.didi1309.coummity.provider.GitbubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        GithubUser user = gitbubProvider.getUser(accessToken);
        if (user!=null){
            request.getSession().setAttribute("User",user);
            return "redirect:/";
        }else {
            return "redirect:index";
        }
    }
}
