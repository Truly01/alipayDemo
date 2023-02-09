package com.truly.alipayDemo.constants;

/**
 * @author truly
 * @date 2023/2/8 13:33
 * @Description:
 */
public interface Constants {

    //响应请求成功
    String HTTP_RES_CODE_200_VALUE="success";
    //响应请求失败
    String HTTP_RES_CODE_500_VALUE="fail";
    //响应请求成功code
    Integer HTTP_RES_CODE_200=200;
    //系统内部错误
    Integer HTTP_RES_CODE_500=500;

    //支付token
    String TOKEN_PAY="TOKEN_PAY";
    //用户有效期90天
    Long TOKEN_MEBER_TIME=(long)(60*60*24*90);
    //支付时效15分钟
    Long TOKEN_PAT_TIME=(long)(60*15);
    //支付成功
    String PAY_SUCCESS="success";
    //支付失败
    String PAY_FAIL="fail";

}
