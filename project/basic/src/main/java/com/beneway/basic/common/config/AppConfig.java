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

package com.beneway.basic.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${appConfig.excludePaths}")
    private String[] excludePaths;

    @Value("${appConfig.moduleType}")
    private String moduleType;

    public void setActive(String active) {
        if (this.active == null){
            this.active = active;
        }
    }

    public void setExcludePaths(String[] excludePaths) {
        if (this.excludePaths == null) {
            this.excludePaths = excludePaths;
        }
    }

    public void setModuleType(String moduleType) {
        if (this.moduleType == null) {
            this.moduleType = moduleType;
        }
    }
}
