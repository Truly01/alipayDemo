package com.truly.alipayDemo.BaseApi;


import lombok.Getter;
import lombok.Setter;

/**
 * @author truly
 * @date 2023/2/8 11:15
 * @Description:
 */
@Getter
@Setter
public class ResponseBase {
    private Integer Code;
    private String Msg;
    private Object Data;

    public ResponseBase(Integer code, String msg, Object data) {
        this.Code = code;
        this.Msg = msg;
        this.Data = data;
    }

    public ResponseBase() {
    }
}
