package com.atguigu.gulimall.auth.controller;

/**
 * @author Mrwsn
 * @createTime 2023/1/2 18:41
 */

import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.constant.AuthServerConstant;
import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.auth.feign.MemberFeignService;
import com.atguigu.gulimall.auth.feign.ThirdPartFeignService;
import com.atguigu.gulimall.auth.feign.UserLoginVo;
import com.atguigu.gulimall.auth.vo.UserRegistVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    ThirdPartFeignService thirdPartFeignService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    MemberFeignService memberFeignService;

    @ResponseBody
    @GetMapping("/sms/sendcode")
    public R sendCode(@RequestParam("phone") String phone) {
        String key = AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone;
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(redisCode)) {
            Long ttl = stringRedisTemplate.opsForValue().getOperations().getExpire(key);
            Long expire = 120L;
            if (ttl >= expire) {
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(),BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        String code = UUID.randomUUID().toString().substring(0, 5);
        stringRedisTemplate.opsForValue().set(key, code, 3, TimeUnit.MINUTES);
        thirdPartFeignService.sendCode(phone,code);
        return R.ok();
    }

    @PostMapping("/regist")
    public String regist(@Valid UserRegistVo vo, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,(oldValue,newValue) -> oldValue));
            redirectAttributes.addFlashAttribute("errors",errors);
            //校验出错转发到注册页
            return "redirect:http://auth.gulimall.com/reg.html";
        }
        String code = vo.getCode();
        String key = AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone();
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(redisCode)) {
            if (code.equals(redisCode)) {
                stringRedisTemplate.delete(key);
                //注册
                R r = memberFeignService.regist(vo);
                if (r.getCode() == 0) {
                    //成功
                    return "redirect:http://auth.gulimall.com/login.html";
                }else {
                    //失败
                    Map<String, String> errors = new HashMap<>();
                    errors.put("msg",r.getData("msg",new TypeReference<String>(){}));
                    redirectAttributes.addFlashAttribute("errors",errors);
                    return "redirect:http://auth.gulimall.com/reg.html";
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("code","验证码错误");
                redirectAttributes.addFlashAttribute("errors",errors);
                //校验出错转发到注册页
                return "redirect:http://auth.gulimall.com/reg.html";
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("code","验证码已失效");
            redirectAttributes.addFlashAttribute("errors",errors);
            //校验出错转发到注册页
            return "redirect:http://auth.gulimall.com/reg.html";
        }
    }
    @PostMapping("/login")
    public String login(UserLoginVo vo, RedirectAttributes redirectAttributes) {
        R r = memberFeignService.login(vo);
        if (r.getCode() == 0) {
            //成功
            return "redirect:http://gulimall.com/";
        }else {
            Map<String, String> errors = new HashMap<>();
            errors.put("msg",r.getData("msg", new TypeReference<String>(){}));
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://auth.gulimall.com/login.html";
        }

    }


}
