#Spring项目登录流程：<br/>
                用户请求登录，跳转github登录，callback携带code<br/>
                后台通过code访问 https://github.com/login/oauth/access_token<br/>
                获得accessToken<br/>
                携带accessToken访问userapi然后获得返回的用户信息<br/>
---
19.11.14<br/>
集成H2数据库 <br/>
---
19.11.22<br/>
实现持久化登录状态获取<br/>
登录成功后，写入cookie( response.addCookie(new Cookie("token",token));),访问IndexController时,获得cookie，是否存在token，token是否存在数据库中
---


           
                