package com.truly.alipayDemo.service.impl;

import com.truly.alipayDemo.BaseApi.BaseApiService;
import com.truly.alipayDemo.BaseApi.ResponseBase;
import com.truly.alipayDemo.constants.Constants;
import com.truly.alipayDemo.dao.PaymentInfoDao;
import com.truly.alipayDemo.entity.PaymentInfo;
import com.truly.alipayDemo.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PayOrderServiceImpl extends BaseApiService implements PayOrderService {
//    @Autowired
//    private OrderServiceFeign orderServiceFeign;
    @Autowired
    private PaymentInfoDao paymentInfoDao;


    @Transactional
    public String payOrder(@RequestParam("orderId") String orderId,@RequestParam("temp") Integer temp) {
        PaymentInfo paymentInfo = paymentInfoDao.getByOrderIdPayInfo(orderId);
        if(paymentInfo==null){
            return Constants.PAY_FAIL;
        }
        Integer state = paymentInfo.getState();
        if (state==1){
            return Constants.PAY_SUCCESS;
        }
        //支付宝交易号
        String tradeNo ="1234567898888" ;

        //付款金额
//        String totalAmount =params.get("total_amount") ;
        //判断实际付款金额与商品金额是否一致
//            if (!totalAmount.equals(商品金额)){
//                return "异常订单！";然后退回金额
//            }
        //修改支付详情表
        paymentInfo.setState(1);
        paymentInfo.setPayMessage("test");
        paymentInfo.setPlatformorderId(tradeNo);
        //手动begin
        Integer updateResult=paymentInfoDao.updatePayInfo(paymentInfo);
        if(updateResult<=0){
            return Constants.PAY_FAIL;
        }

        //调用订单接口，修改支付状态
//        ResponseBase orderResult = orderServiceFeign.updateOrderIdInfo(1L, tradeNo, orderId);
//        if (!orderResult.getRtnCode().equals(Constants.HTTP_RES_CODE_200)){
//            return Constants.PAY_FAIL;
//        }
        //手动commit
        int i=1/temp;
        return Constants.PAY_SUCCESS;
    }
}
