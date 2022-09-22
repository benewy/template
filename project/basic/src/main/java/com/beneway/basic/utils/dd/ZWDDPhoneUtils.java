package com.beneway.basic.utils.dd;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.xxpt.gateway.shared.api.request.OapiMessageWorkNotificationRequest;
import com.alibaba.xxpt.gateway.shared.api.request.OapiRpcOauth2DingtalkAppUserJsonRequest;
import com.alibaba.xxpt.gateway.shared.api.response.OapiMessageWorkNotificationResponse;
import com.alibaba.xxpt.gateway.shared.api.response.OapiRpcOauth2DingtalkAppUserJsonResponse;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.IntelligentGetClient;
import com.alibaba.xxpt.gateway.shared.client.http.IntelligentPostClient;
import com.beneway.basic.system.token.dao.TokenDao;
import com.beneway.basic.utils.dd.entity.msg.Card;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.taobao.api.internal.util.json.JSONWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Lazy
@Component
public class ZWDDPhoneUtils {

    @Value("${dd-config.phone.key}")
    private String key;

    @Value("${dd-config.phone.secret}")
    private String secret;

    @Value("${dd-config.domainname}")
    private String domainname;

    @Value("${dd-config.tenantId}")
    private String tenantId;

    private final static Gson gson = new Gson();

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

    private static final String TOKEN_TYPE = "P";

    private String getToken() {
        String token = tokenDao.getToken(TOKEN_TYPE);
        log.info("获取token：" + token);
        return token;
    }

    public void flushToken() {
        zwddBaseUtils.flushToken(key, secret, getExecutableClient(), TOKEN_TYPE);
    }

    public JsonObject getUserInfo(String auth_code) {
        try {
            OapiRpcOauth2DingtalkAppUserJsonRequest req =
                    new OapiRpcOauth2DingtalkAppUserJsonRequest();
            req.setAccess_token(getToken());
            req.setAuth_code(auth_code);
            IntelligentPostClient client = getExecutableClient()
                    .newIntelligentPostClient(req.getApiUrl());
            OapiRpcOauth2DingtalkAppUserJsonResponse apiResult = client.post(req);

            log.info("政务钉钉用户返回数据：" + apiResult.getContent());
            if (apiResult.getSuccess()) {
                String content = apiResult.getContent();
                JsonObject data = ZWDDBaseUtils.getJsonData(content);
                log.info("获取政务钉钉用户详情成功 返回数据：" + data);
                return data;
            } else {
                log.error("获取政务钉钉用户详情失败 返回数据：" + apiResult);
            }
        } catch (JsonSyntaxException e) {
            log.error("获取政务钉钉用户详情数据解析失败", e);
        } catch (Exception e) {
            log.error("获取政务钉钉用户详情数据请求失败", e);
        }
        return null;
    }

    /**
     * 发送工作卡片通知
     * @param accountIdList
     */
    public void sendWorkCardMsg(List<String> accountIdList, Card card) {
        log.info("================== 发送工作卡片通知 开始 ==================");
        // 发送消息内容
        Map<String, Object> param = new HashMap<>();
        param.put("msgtype", "action_card");
        param.put("action_card", card);
        String msg = (new JSONWriter(false, false, true)).write(param);
        // 最多一个发送20个用户
        int limit = 20;
        for (int i = 0; i < accountIdList.size(); i += limit) {
            OapiMessageWorkNotificationRequest req =
                    new OapiMessageWorkNotificationRequest();
            req.setTenantId(tenantId);
            // 封装发送用户id串
            String receiverIds = CollUtil.sub(accountIdList, i, i + limit).stream().collect(Collectors.joining(","));
            req.setReceiverIds(receiverIds);
            log.info("发送用户id：" + receiverIds);
            // 封装消息内容
            req.setMsg(msg);
            log.info("消息内容：" + msg);

            // 发送消息
            IntelligentGetClient intelligentGetClient = getExecutableClient().newIntelligentGetClient(req.getApiUrl());
            OapiMessageWorkNotificationResponse response = intelligentGetClient.get(req);
            log.info("消息发送结果：" + gson.toJson(response));
        }
        log.info("================== 发送工作卡片通知 结束 ==================");
    }

}
