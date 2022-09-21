package com.beneway.basic.utils.dd;

import com.alibaba.xxpt.gateway.shared.api.request.OapiRpcOauth2GetuserinfoBycodeJsonRequest;
import com.alibaba.xxpt.gateway.shared.api.response.OapiRpcOauth2GetuserinfoBycodeJsonResponse;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.IntelligentPostClient;
import com.beneway.basic.system.token.dao.TokenDao;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2021/8/18
 * @time: 16:49
 */
@Log4j2
@Lazy
@Component
public class ZWDDQrcodeUtils {

    @Value("${dd-config.qrcode.key}")
    private String key;

    @Value("${dd-config.qrcode.secret}")
    private String secret;

    @Value("${dd-config.domainname}")
    private String domainname;

    /**
     * 获取之前都要重新设置防止被替换
     *
     * @return
     */
    private ExecutableClient getExecutableClient() {
        ExecutableClient executableClient = ExecutableClient.getInstance();
        executableClient.setAccessKey(key);
        executableClient.setSecretKey(secret);
        executableClient.setDomainName(domainname);
        executableClient.setProtocal("https");
        executableClient.init();
        return executableClient;
    }

    @Resource
    private TokenDao tokenDao;

    @Resource
    private ZWDDBaseUtils zwddBaseUtils;

    private static final String TOKEN_TYPE = "Q";

    private String getToken() {
        return tokenDao.getToken(TOKEN_TYPE);
    }

    public void flushToken() {
        zwddBaseUtils.flushToken(key, secret, getExecutableClient(), TOKEN_TYPE);
    }

    /**
     * 根据code获取用户详情
     *
     * @param code
     * @return
     */
    public JsonObject getUserInfoQrcode(String code) {
        try {
            OapiRpcOauth2GetuserinfoBycodeJsonRequest req =
                    new OapiRpcOauth2GetuserinfoBycodeJsonRequest();
            req.setAccess_token(getToken());
            req.setCode(code);

            IntelligentPostClient client = getExecutableClient()
                    .newIntelligentPostClient(req.getApiUrl());
            OapiRpcOauth2GetuserinfoBycodeJsonResponse apiResult = client.post(req);
            log.info("政务钉钉扫码返回数据：" + apiResult.getContent());
            if (apiResult.getSuccess()) {
                String content = apiResult.getContent();
                JsonObject data = ZWDDBaseUtils.getJsonData(content);
                log.info("获取政务钉钉扫码用户详情成功 返回数据：" + data);
                return data;
            } else {
                log.error("获取政务钉钉扫码用户详情失败 返回数据：" + apiResult);
            }
        } catch (JsonSyntaxException e) {
            log.error("获取政务钉钉扫码用户详情数据解析失败", e);
        } catch (Exception e) {
            log.error("获取政务钉钉扫码用户详情数据请求失败", e);
        }
        return null;
    }

}
