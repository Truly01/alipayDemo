package com.truly.alipayDemo.controller;

import com.alipay.api.AlipayApiException;
import com.truly.alipayDemo.BaseApi.ResponseBase;
import com.truly.alipayDemo.entity.PaymentInfo;
import com.truly.alipayDemo.service.impl.PayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author truly
 * @date 2023/2/8 11:13
 * @Description: 支付接口
 */
@Api("支付操作接口")
@RestController
public class PayController {
    @RequestMapping("/return")
    public void test() {
        System.out.println("=============return================");

    }
    @RequestMapping("/notify")
    public void notify1() {
        System.out.println("============notify=================");

    }
    @Autowired
    private PayServiceImpl payServiceImpl;
    @ApiOperation("创建支付订单信息")
    @GetMapping("/createPayToken")
    public ResponseBase createToken(@RequestBody PaymentInfo paymentInfo) {
        return payServiceImpl.createToken(paymentInfo);

    }
    @ApiOperation("查找支付订单信息")
    @PostMapping("/findPayToken")
    public ResponseBase findPayToken(@RequestParam("payToken") String payToken) throws AlipayApiException {
        return payServiceImpl.findPayToken(payToken);
    }
}
