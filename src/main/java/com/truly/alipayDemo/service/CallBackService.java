package com.truly.alipayDemo.service;

import com.truly.alipayDemo.BaseApi.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequestMapping("/callBack")
public interface CallBackService {
    //同步通知
    @RequestMapping("/synCallBack")
    public ResponseBase synCallBack(@RequestParam Map<String, String> params);
    //异步通知
    @RequestMapping("/asynCallBack")
    public String asynCallBack(@RequestParam Map<String, String> params);
}
