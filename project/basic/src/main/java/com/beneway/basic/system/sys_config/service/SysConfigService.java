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

package com.beneway.basic.system.sys_config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beneway.basic.system.sys_config.enums.SysConfigKeyEnum;
import com.beneway.basic.system.sys_config.po.SysConfig;

/**
 * 系统配置表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-18 10:16:22
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 获取配置信息
     * @param keyEnum
     */
    String getConfigValue(SysConfigKeyEnum keyEnum);

}

