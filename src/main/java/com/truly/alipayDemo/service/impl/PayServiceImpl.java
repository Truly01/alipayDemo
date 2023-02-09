package com.truly.alipayDemo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.truly.alipayDemo.BaseApi.BaseApiService;
import com.truly.alipayDemo.BaseApi.ResponseBase;
import com.truly.alipayDemo.config.AliPayConfig;
import com.truly.alipayDemo.constants.Constants;
import com.truly.alipayDemo.dao.PaymentInfoDao;
import com.truly.alipayDemo.entity.PaymentInfo;
import com.truly.alipayDemo.service.PayService;
import com.truly.alipayDemo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class PayServiceImpl extends BaseApiService implements PayService {
    @Autowired
    private PaymentInfoDao paymentInfoDao;
    @Override
    public ResponseBase createToken(PaymentInfo paymentInfo) {
        //1.创建插入支付请求信息
        Integer retrunNum = paymentInfoDao.savePaymentType(paymentInfo);
        if(retrunNum<=0){
            return setResultError("创建支付订单信息失败！");
        }
        //2.生成对应token
        String payToken = TokenUtil.getPayToken();
        //3.存放redis中
        baseRedisService.setString(payToken,paymentInfo.getId().toString(), Constants.TOKEN_PAT_TIME);
        //7.返回token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payToken",payToken);
        return setResultSuccess(jsonObject);
    }

    @Override
    @RequestMapping("/findPayToken")
    public ResponseBase findPayToken(@RequestParam("payToken") String payToken) throws AlipayApiException {
        //1.参数验证
        if (StringUtils.isEmpty(payToken)){
            return setResultError("支付token为空，请重试！");
        }
        //2.判断token有效期
        //3.使用token查找Redis，找到对应支付id
        String payId =(String) baseRedisService.getString(payToken);
        if (StringUtils.isEmpty(payId)){
            return setResultError("支付请求已超时");
        }
        //4.根据对应id进行下单
        Long paiIdLong = Long.parseLong(payId);
        //5.使用支付id查询数据库得出支付信息
        PaymentInfo paymentInfo = paymentInfoDao.getPaymentInfo(paiIdLong);
        if (paymentInfo==null){
            return setResultError("未找到支付信息");
        }
        //6.对接支付代码,返回提交支付form表单元素给客户端
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl, AliPayConfig.app_id, AliPayConfig.merchant_private_key, "JSON", AliPayConfig.charset, AliPayConfig.alipay_public_key, AliPayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AliPayConfig.return_url);
        alipayRequest.setNotifyUrl(AliPayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = paymentInfo.getOrderId();
        //付款金额，必填
        String total_amount = paymentInfo.getPrice()+"";
        //订单名称，必填
        String subject = "测试订单";
        //商品描述，可空
        String body = "测试";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payHtml",result);
        return setResultSuccess(jsonObject);
    }
}
