package com.beneway.basic.system.sys_agency.vo;

import com.beneway.basic.system.sys_agency.po.SysAgency;
import lombok.Data;

import java.util.List;

/**
 * @author lichen
 * @email dcam00r0@qq.com
 * @date 2022/5/30 15:07
 */
@Data
public class SysAgencyVo extends SysAgency {
    private List<SysAgencyVo> children;
}
