package com.truly.alipayDemo.BaseApi;

import com.truly.alipayDemo.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author truly
 * @date 2023/2/8 13:26
 * @Description:
 */
public class BaseApiService {
    @Autowired
    protected BaseRedisService baseRedisService;
    //返回失败
    public ResponseBase setResultError(String msg){
        return setResult(Constants.HTTP_RES_CODE_500,msg,null);
    };
    //返回自定义失败码及消息
    public ResponseBase setResultError(Integer code,String msg){
        return setResult(code,msg,null);
    };
    //返回成功传data值
    public ResponseBase setResultSuccess(Object data){
        return setResult(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,data);
    };
    //返回成功传消息
    public ResponseBase setResultSuccess(String msg){
        return setResult(Constants.HTTP_RES_CODE_200,msg,null);
    };
    //返回成功但没data值
    public ResponseBase setResultSuccess(){
        return setResult(Constants.HTTP_RES_CODE_200,Constants.HTTP_RES_CODE_200_VALUE,null);
    };
    //通用封装
    private ResponseBase setResult(Integer code,String msg,Object data){
        return  new ResponseBase(code,msg,data);
    };
}
