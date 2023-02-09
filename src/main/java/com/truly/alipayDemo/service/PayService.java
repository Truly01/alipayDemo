package com.truly.alipayDemo.service;


import com.truly.alipayDemo.BaseApi.ResponseBase;
import com.truly.alipayDemo.entity.PaymentInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/pay")
public interface PayService {
    //创建支付令牌
    @RequestMapping("/createToken")
    public ResponseBase createToken(@RequestBody PaymentInfo paymentInfo);
    //使用支付令牌
    @RequestMapping("/findPayToken")
    public ResponseBase findPayToken(@RequestParam("payToken") String payToken) throws Exception;
}
