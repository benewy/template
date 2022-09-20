package com.beneway.basic.system.sys_filter_user.vo;

import com.beneway.basic.system.sys_filter_user.po.SysFilterUser;
import lombok.Data;

import java.util.List;

/**
 * Create by zhy on 2022/3/14 15:11
 */
@Data
public class SysFilterUserVo extends SysFilterUser {

    private List unitDataList;

    private List userDataList;

    private String unitModeDesc;

    private String userModeDesc;

}
