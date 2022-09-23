/**
 * 版权所有 2022 - 至今 为 杭州融惠数据科技有限公司 所有
 *
 * 版权说明使用 GNU Lesser General Public License v3.0 or later 许可证;
 * 除非遵守许可说明，否则您无权使用此文件。
 * 您可以在以下网址获取许可证的副本
 *          https://spdx.org/licenses/LGPL-3.0-or-later.html
 * 除非适用法律要求或书面同意，否则软件
 * 根据许可分发是在“原样”基础上分发的，
 * 不提供任何明示或暗示的保证或条件。
 * 请参阅许可证以了解特定语言的管理权限和
 * 许可证下的限制。
 */

package com.beneway.basic.utils.dd;

import com.alibaba.xxpt.gateway.shared.api.request.OapiGettokenJsonRequest;
import com.alibaba.xxpt.gateway.shared.api.response.OapiGettokenJsonResponse;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.GetClient;
import com.alibaba.xxpt.gateway.shared.client.http.IntelligentGetClient;
import com.alibaba.xxpt.gateway.shared.client.http.PostClient;
import com.alibaba.xxpt.gateway.shared.client.http.api.OapiSpResultContent;
import com.beneway.basic.system.token.dao.TokenDao;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @email 2434017367@qq.com
 * @author: zhy
 * @date: 2021/8/18
 * @time: 17:13
 */
@Log4j2
@Component
public class ZWDDBaseUtils {

    @Resource
    private TokenDao tokenDao;

    public static boolean getJsonSuccess(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        boolean success = jsonObject.get("success").getAsBoolean();
        if (success) {
            jsonObject = jsonObject.getAsJsonObject("content");
            boolean d = jsonObject.get("success").getAsBoolean();
            return d;
        }
        return false;
    }

    public static JsonObject getJsonData(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject("content");
        jsonObject = jsonObject.getAsJsonObject("data");
        return jsonObject;
    }

    private static final RestTemplate restTemplate = new RestTemplate();

    public static String requestProxy(String requestProxyUrl, String type, String api, Map<String, String> map) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("type", type);
        requestMap.put("api", api);
        requestMap.put("map", map);
        String body = restTemplate.postForObject(requestProxyUrl, requestMap, String.class);
        return body;
    }

    public static String requestProxy(ExecutableClient executableClient, String type, String api, Map<String, String> map) {
        if ("get".equalsIgnoreCase(type)) {
            GetClient getClient = executableClient.newGetClient(api);
            if (map != null) {
                Set<Map.Entry<String, String>> entries = map.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    getClient.addParameter(key, value);
                }
            }
            String s = getClient.get();
            return s;
        } else if ("post".equalsIgnoreCase(type)) {
            PostClient postClient = executableClient.newPostClient(api);
            if (map != null) {
                Set<Map.Entry<String, String>> entries = map.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    postClient.addParameter(key, value);
                }
            }
            String s = postClient.post();
            return s;
        }

        return null;
    }

    public void flushToken(String key, String secret, ExecutableClient executableClient, String TOKEN_TYPE) {
        try {
            OapiGettokenJsonRequest req = new OapiGettokenJsonRequest();
            req.setAppkey(key);
            req.setAppsecret(secret);

            IntelligentGetClient client = executableClient
                    .newIntelligentGetClient(req.getApiUrl());
            OapiGettokenJsonResponse apiResult = client.get(req);
//            System.err.println(apiResult.getContent().getData());
            if (apiResult.getSuccess()) {
                OapiSpResultContent content = apiResult.getContent();
                if (content.getSuccess()) {
                    JsonObject data = new JsonParser().parse(content.getData()).getAsJsonObject();
                    String token = data.get("accessToken").getAsString();
                    log.info("获取政务钉钉token：" + token + "，type：" + TOKEN_TYPE);
                    String myToken = tokenDao.getToken(TOKEN_TYPE);
                    if (null == myToken) {
                        tokenDao.insert(token, TOKEN_TYPE);
                    } else if (!myToken.equals(token)) {
                        tokenDao.update(token, TOKEN_TYPE);
                    }
                }
            } else {
                log.error("获取政务钉钉token失败 返回数据：" + apiResult);
            }
        } catch (JsonSyntaxException e) {
            log.error("获取政务钉钉token数据解析失败", e);
        } catch (Exception e) {
            log.error("获取政务钉钉token数据请求失败", e);
        }
    }

}
