package com.truly.alipayDemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.truly.alipayDemo.BaseApi.BaseApiService;
import com.truly.alipayDemo.BaseApi.ResponseBase;
import com.truly.alipayDemo.config.AliPayConfig;
import com.truly.alipayDemo.constants.Constants;
import com.truly.alipayDemo.dao.PaymentInfoDao;
import com.truly.alipayDemo.entity.PaymentInfo;
import com.truly.alipayDemo.service.CallBackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Service
public class CallBackServiceImpl extends BaseApiService implements CallBackService {
    @Autowired
    private PaymentInfoDao paymentInfoDao;
//    @Autowired
//    private OrderServiceFeign orderServiceFeign;
    @Override
    public ResponseBase synCallBack(@RequestParam Map<String, String> params) {
        //1.日志记录
        log.info("###支付宝同步通知synCallBack开始###，params:{}",params);
        //2.验签操作
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type);


        //——请在这里编写您的程序（以下代码仅作参考）——
        if(!signVerified) {
            return setResultError("验签失败!");
        }
            //商户订单号
            String outTradeNo = params.get("out_trade_no");

            //支付宝交易号
            String tradeNo =params.get("trade_no") ;

            //付款金额
            String totalAmount =params.get("total_amount") ;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("outTradeNo",outTradeNo);
        jsonObject.put("tradeNo",tradeNo);
        jsonObject.put("totalAmount",totalAmount);
        return setResultSuccess(jsonObject);
        } catch (AlipayApiException e) {
            log.error("###支付宝同步通知异常！");
            return setResultError("系统错误！");
        }finally {
            log.info("###支付宝同步通知synCallBack结束###，params:{}",params);
        }
    }

    @Override
    public synchronized String asynCallBack(@RequestParam Map<String, String> params) {
        //1.日志记录
        log.info("###支付宝异步通知asynCallBack开始###，params:{}",params);
        //2.验签操作
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.alipay_public_key, AliPayConfig.charset, AliPayConfig.sign_type);


            //——请在这里编写您的程序（以下代码仅作参考）——
            if(!signVerified) {
                return Constants.PAY_FAIL;
            }
            //商户订单号
            String outTradeNo = params.get("out_trade_no");
            PaymentInfo paymentInfo = paymentInfoDao.getByOrderIdPayInfo(outTradeNo);
            if(paymentInfo==null){
                return Constants.PAY_FAIL;
            }
            Integer state = paymentInfo.getState();
            if (state==1){
                return Constants.PAY_SUCCESS;
            }
            //支付宝交易号
            String tradeNo =params.get("trade_no") ;

            //付款金额
            String totalAmount =params.get("total_amount") ;
            //判断实际付款金额与商品金额是否一致
//            if (!totalAmount.equals(商品金额)){
//                return "异常订单！";然后退回金额
//            }
            JSONObject jsonObject = new JSONObject();
            //修改支付详情表
            paymentInfo.setState(1);
            paymentInfo.setPayMessage(params.toString());
            paymentInfo.setPlatformorderId(tradeNo);
            //手动begin
            Integer updateResult=paymentInfoDao.updatePayInfo(paymentInfo);
            if(updateResult<=0){
                return Constants.PAY_FAIL;
            }

            //调用订单接口，修改支付状态
//            ResponseBase orderResult = orderServiceFeign.updateOrderIdInfo(1L, tradeNo, outTradeNo);
//            if (!orderResult.getCode().equals(Constants.HTTP_RES_CODE_200)){
//                return Constants.PAY_FAIL;
//            }
            //手动commit
            return Constants.PAY_SUCCESS;
        } catch (AlipayApiException e) {
            log.error("###支付宝异步通知异常！");
            return Constants.PAY_FAIL;
        }finally {
            log.info("###支付宝异步通知asynCallBack结束###，params:{}",params);
        }
    }
}
