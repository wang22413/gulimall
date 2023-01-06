package com.atguigu.gulimall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.utils.HttpUtils;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.auth.feign.MemberFeignService;
import com.atguigu.gulimall.auth.vo.MemberRespVo;
import com.atguigu.gulimall.auth.vo.SocialUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mrwsn
 * @createTime 2023/1/5 16:35
 */
@Slf4j
@Controller
public class OAuth2Controller {

    @Autowired
    MemberFeignService memberFeignService;

    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session) throws Exception {
        Map<String, String> header = new HashMap<>();
        Map<String, String> query = new HashMap<>();

        Map<String, String> map = new HashMap<>();
        map.put("client_id","2506187944");
        map.put("client_secret","453cbe71b981a92636477ce9c640dc1a");
        map.put("grant_type","authorization_code");
        map.put("redirect_uri","http://auth.gulimall.com/oauth2.0/weibo/success");
        map.put("code",code);
        //1、工具code换取accessToken
        HttpResponse response = HttpUtils
                .doPost("https://api.weibo.com","/oauth2/access_token","post",header,query,map);
//        2、处理
         if (response.getStatusLine().getStatusCode() == 200) {
             String json = EntityUtils.toString(response.getEntity());
             SocialUserVo socialUser = JSON.parseObject(json, SocialUserVo.class);
             R oauthLogin = memberFeignService.oauthLogin(socialUser);
             if (oauthLogin.getCode() == 0) {
                 MemberRespVo data = oauthLogin.getData("data", new TypeReference<MemberRespVo>() {});
                 log.info(data.toString());
                 session.setAttribute("loginUser",data);
                 return "redirect:http://gulimall.com";
             }else {
                 return "redirect:http://auth.gulimall.com/login.html";
             }
         }else {
             return "redirect:http://auth.gulimall.com/login.html";
         }
    }

}
