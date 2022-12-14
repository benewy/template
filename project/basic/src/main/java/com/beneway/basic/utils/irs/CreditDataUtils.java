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

package com.beneway.basic.utils.irs;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.beneway.basic.exception.RRException;
import com.beneway.basic.exception.ResultException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/26 14:52
 *
 * irs 信用档案查询
 */
@Log4j2
@Component
public class CreditDataUtils {

    /**
     * 请求地址
     */
    private static final String reqUrl = "http://10.25.16.202:8091/interface.html";
    /**
     * 查询部门名称全称
     */
    private static final String deptName = "舟山市司法局（舟山市行政复议局）";
    /**
     * 查询部门统一社会信用代码
     */
    private static final String deptCode = "11330900002634306E";
    /**
     * 主体类型：S 社会组织，E 企业
     */
    private static final String ztlx = "E";

    /**
     * 查询企业信用报告
     * @param uscc  企业、社会组织统一社会信用代码
     * @return 信用报告url地址
     */
    public static String query(String uscc) {
        HttpRequest request = HttpUtil.createGet(reqUrl);
        request.form("appKey", "0b4ed8e467094cc398bb0b808a6c8de4");
        request.form("deptName", deptName);
        request.form("deptCode", deptCode);
        request.form("ztlx", ztlx);
        request.form("uscc", uscc);
        request.form("purpose", "企业资信查询");
        request.form("function", "creditarchives");

        log.info("查询企业信用报告，统一社会信用代码：" + uscc);

        request.setReadTimeout(10000);
        HttpResponse execute = request.execute();
        String body = execute.body();

        log.info("查询企业信用报告，请求返回信息：" + body);

        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        boolean success = jsonObject.get("success").getAsBoolean();
        if (success) {
            String fileUrl = jsonObject.get("data").getAsJsonObject().get("daUrl").getAsString();
            return fileUrl;
        } else {
            String msg = jsonObject.get("msg").getAsString();
            throw new ResultException(HttpStatus.INTERNAL_SERVER_ERROR,"查询企业信用报告获取失败，" + msg);
        }
    }

    /**
     * 查询企业信用评价
     * @param uscc
     * @return
     */
    public static Map<String, Object> queryAssess(String uscc) {
        HttpRequest request = HttpUtil.createGet(reqUrl);
        request.form("appKey", "fa3ab834597e45e389e6bc3410823888");
        request.form("deptName", deptName);
        request.form("deptCode", deptCode);
        request.form("ztlx", ztlx);
        request.form("uscc", uscc);
        request.form("purpose", "企业资信查询");
        request.form("function", "creditassess");

//        log.info("查询企业信用评报告，统一社会信用代码：" + uscc);

        request.setReadTimeout(10000);
        HttpResponse execute = request.execute();
        String body = execute.body();

//        log.info("查询企业信用评报告，请求返回信息：" + body);

        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        boolean success = jsonObject.get("success").getAsBoolean();
        if (success) {
            Map<String, Object> map = new HashMap<>();
            map.put("assessLevel", jsonObject.get("data").getAsJsonObject().get("pjdj").getAsString());
            map.put("creditScore", jsonObject.get("data").getAsJsonObject().get("xyf").getAsString());
            String ztmc = jsonObject.get("data").getAsJsonObject().get("ztmc").getAsString();
            if (StringUtils.isNotEmpty(ztmc)) {
                map.put("ztmc", ztmc);
                map.put("belong", querySeriousdishonesty(uscc, ztmc));
            }
            return map;
        } else {
            String msg = jsonObject.get("msg").getAsString();
            throw new ResultException(HttpStatus.INTERNAL_SERVER_ERROR,"查询企业信用评报告获取失败，" + msg);
        }
    }

    public static boolean querySeriousdishonesty(String uscc, String ztmc) {
        HttpRequest request = HttpUtil.createGet(reqUrl);
        request.form("appKey", "2e2b40c45f1248409400c500b81aabb4");
        request.form("deptName", deptName);
        request.form("deptCode", deptCode);
        request.form("ztlx", ztlx);
        request.form("ztmc", ztmc);
        request.form("uscc", uscc);
        request.form("purpose", "严重失信名单查询");
        request.form("function", "seriousdishonesty");

        log.info("查询严重失信名单报告，统一社会信用代码：" + uscc);

        request.setReadTimeout(10000);
        HttpResponse execute = request.execute();
        String body = execute.body();

        log.info("查询严重失信名单报告，请求返回信息：" + body);

        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        boolean success = jsonObject.get("success").getAsBoolean();
        if (success) {
            boolean belong = jsonObject.get("data").getAsJsonObject().get("belong").getAsBoolean();
            return belong;
        } else {
            String msg = jsonObject.get("msg").getAsString();
            throw new ResultException(HttpStatus.INTERNAL_SERVER_ERROR,"查询严重失信名单报告获取失败，" + msg);
        }
    }
}
