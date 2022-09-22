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

package com.beneway.basic.system.sys_message.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beneway.basic.system.sys_message.fo.SysMessageQueryFo;
import com.beneway.basic.system.sys_message.po.SysMessage;
import com.beneway.basic.system.sys_message.vo.SysMessageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-06 19:04:42
 */
@Mapper
@Repository
public interface SysMessageDao extends BaseMapper<SysMessage> {
    /**
     * 获取消息，分页
     * @author  yhz
     * @create  2022/4/11 17:02
     * @Param
     * @return
     **/
    Page<SysMessageVo> queryPageMessage(Page page, @Param("param") SysMessageQueryFo sysMessageQueryFo);
}
