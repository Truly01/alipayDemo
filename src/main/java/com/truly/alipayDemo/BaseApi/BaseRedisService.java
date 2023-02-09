package com.truly.alipayDemo.BaseApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class BaseRedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, String data, Long timeout) {
        this.setObject(key, data, timeout);
    }

    public Object getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

    private void setObject(String key, Object data, Long timeout) {
        if (StringUtils.isEmpty(key) || data == null)
            return;
        if (data instanceof String) {
            String value = (String) data;
            stringRedisTemplate.opsForValue().set(key, value);
            if (timeout != null) {
                stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return;
        }
        if (data instanceof List) {
            List<String> value = (List<String>) data;
            for (String oneValue : value) {
                stringRedisTemplate.opsForList().leftPush(key, oneValue);
            }
            if (timeout != null) {
                stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return;
        }
    }

    private Object getObject(String key) {
        if (StringUtils.isEmpty(key))
            return null;
     return null;
    }
}
