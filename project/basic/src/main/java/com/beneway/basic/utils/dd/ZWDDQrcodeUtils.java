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
