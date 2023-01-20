package com.atguigu.gulimall.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.gulimall.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2021000122605924";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCvGHfqLDgridWOg4AE3yLdjuFS7klbKzDggx5Q5NNUDLm+71mMZGjGZHjMZ7+3RioGbsi2xTWLpyH5+Q5g+qtCCjRDbi5+vBW6HTP5vVdQzfiINcEMg3gD6o04nHHntcEe9vnP83s3uJgLuicbx++G1H1xE5KndoB8vcwJZ1RkGYqL7AbvFqSt0MI16S+lxtfYGNvvYh1EceN6XhC+l7Seds4hMM3jfj+pBTFx0/0Q9CRjKpYyPHj4RKXzzmmUXeY4cFUEQoeCsYV5I4IMsh9kVJiqzNwFar+Zc8x+B3q0MEADhRzqDt/1kxkVyhUl0TkVe4DotzEQJjANzTcH4o+5AgMBAAECggEABZg0jKjpCosW5bzR5FX02Ttte6bddSpGH4ZQ+6i0D+VwMeeX5uJ2bpBbQ7y05Xg6c4Yqp5xZf6LRdRk+Gl8oBa4pyVXF6oq77vxr4rjoB07td/yvtGSm+wkNhheJlCu8he7CQKC4+SjOwuEpyrtyrc4wS+KiNvOUW2f9EvfYPHJ+8TLFymSFCs4n2gW99ZFj7DNktFdnamchUAriage3HKqdIzUyCnaqMCwFIsYjdJby9UYhztRF/9npY9xNA1nQi64vySPJ/wag0T2e163jWUuu4LY45te9y0lKZ5tYOCU3PxF/yULpBAhtqxqTNKpHOd4Ga39WAQFJlq8URjw8eQKBgQDT4CPa7aqQj+7XU07M29PRFHOXcw41sn9qgboIaD+SPoy9sQxUsqiSzrQLulRuqAQImTtGgj0lDx4XuEDesn98+ihfl5cjFkl66QG6J2CYsNRXWpqZpJ5pJVB9F4rMsPam5KgBJwHEhFv6u+IKQ1bTCwS2L5zZHZFxz4rloah2gwKBgQDTj3QvSUUGwFV9UuHI1Ua14c2+xaq8Ej9IalAcRm93qUzelIiRruoiW+F94xwDcLZ1vZN9DGlXzsEtb20pHyOem5f6ZaF5G2XcH1w1Rsmyz/t3TSCzsx5qIhhYVmFQ6tfehrBCTRz3Yl5GwJHwFOjt+DesdmHNBnkKiIIEdovsEwKBgCfwfpHMb3SkzT8l4xrW7YziMBACjlEs/rvTDIB9Nk6ZH+q6LzrgGsrh0CrNyxC491mvmO7nqiMMQH30K7ZPK50hgu7SIplooB1dQwgWWC0wWDbHS5Fe6MWlVRDfmTiQu04mmEzvT3P+B2Cyfh+1Je7OiurU+toqqrorh2FWIe2FAoGATHHcmK2QDKV4/qTxQwGV4GjsUE/apsStKzyHIABvjLyXMyJWytV6ctnie/uRhsi2lYrf/WJ7uifSc9l8h0xiMZCldOw+UaWNw+FtDy85+KvggLZU57oEdnNFer4Twgpp4FWo4Pw+SEG6MTSqRyc3F64GaAIKyuvY1u6GlJhFbRMCgYBtfJUS6WOJnh9LkecGCEqVGQCounWndq3Q9BqOAunXWSNj8BtebNYtUxjGTfJifFef55MaAMEi+F5ri7JMii+XOIRvIA2ipXi/07sNkyMZy+xGE3jzyQFW7Oq9G9P5IYv/OF/0G83SfnWWyKldIhA7yZYR3o66P/Prav2CG2qHuA==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtbqWsDWqQwSkVdAH4YE5TuiZWbnJ4MkM4oRRscZnplM66BQYvlkRqemlt4g5ZkfeWy23KD7cVchXwXQm6W5uhIpRLhsohu9UWRNbjLHjUR8j3PrANsPiGhHmgia7wqoeA/ejLSzxWqgFKEUKZMOeUDcaws701xyHH58cP4t7L/+St33Yo4Uka5X92lS5kgbxNyUu2/fGhLFFpuhu7hLh9nfQTxutJdFtwTQC8oOejkMBUBSzp6PO+vyysjKkkBgvqgp+54uFSFqfMafFMlaNu/ndqdrKHhXEvEF1PcEu5Up96HVkJd+VwcWj4/A/26ulmsRHcAKOyMjjo/y8ZRhd1QIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url = "http://pik8an.natappfree.cc/alipay.trade.page.pay-JAVA_UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url = "http://member.gulimall.com/memberOrder.html";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
