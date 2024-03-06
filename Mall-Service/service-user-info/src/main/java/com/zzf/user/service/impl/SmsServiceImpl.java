package com.zzf.user.service.impl;

import com.zzf.common.constant.Constants;
import com.zzf.user.service.SmsService;
import com.zzf.utils.HttpUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zzf
 * @date 2024-01-18
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    @Override
    public void sendValidateCode(String phone) {
//        //查询redis是否已经缓存了验证码
//        String code = redisTemplate.opsForValue().get("phone:code:" + phone);
//        if(StringUtils.hasText(code)) {
//            return;
//        }
        //生成验证码
        String validateCode = RandomStringUtils.randomNumeric(4);
        redisTemplate.opsForValue().set(Constants.REGISTER_VALIDATE_KEY + phone , validateCode , 5 , TimeUnit.MINUTES);
        sendSms(phone , validateCode);
    }

    private void sendSms(String phone, String validateCode) {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "d7347d1a18b346f5a258498c74365d0c";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("content", "code:1234");
        bodys.put("template_id", "CST_ptdie100");  //该模板为调试接口专用，短信下发有受限制，调试成功后请联系客服报备专属模板
        bodys.put("phone_number", phone);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从\r\n\t    \t* https://github.com/aliyun/
             * api-gateway-demo-sign-java/blob/master/src/main/java/com/
             * aliyun/api/gateway/demo/util/HttpUtils.java\r\n\t    \t* 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
