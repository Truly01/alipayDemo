package com.truly.alipayDemo.utils;


import cn.hutool.core.lang.UUID;
import com.truly.alipayDemo.constants.Constants;

public class TokenUtil {
    public static String getPayToken(){
        return Constants.TOKEN_PAY+"-"+ UUID.randomUUID();
    }
}
