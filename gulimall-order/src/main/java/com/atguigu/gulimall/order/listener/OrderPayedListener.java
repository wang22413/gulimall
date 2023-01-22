package com.atguigu.gulimall.order.listener;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.atguigu.gulimall.order.config.AlipayTemplate;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.PayAsyncVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Mrwsn
 * @createTime 2023/1/22 15:50
 */
@RestController
public class OrderPayedListener {

    @Autowired
    OrderService orderService;
    @Autowired
    AlipayTemplate alipayTemplate;

    @PostMapping("/payed/notify")
    public String handleAlipayed(PayAsyncVo vo, HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
//        Map<String, String[]> map = request.getParameterMap();
//        System.out.println("支付宝通知到位了..数据：" + map);
        //验签
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator();iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i=0;i < values.length; i++) {
                valueStr = (i ==values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"),"utf-8");
            params.put(name,valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params,alipayTemplate.getAlipay_public_key(),alipayTemplate.getCharset(),alipayTemplate.getSign_type());
        if (signVerified) {
            System.out.println("签名验证成功...");
            String result = orderService.handlePayResult(vo);
            return result;
        }else {
            System.out.println("签名验证失败...");
            return "error";
        }

    }

}
