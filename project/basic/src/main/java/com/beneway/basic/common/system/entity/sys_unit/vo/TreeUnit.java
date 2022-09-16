package com.beneway.basic.common.system.entity.sys_unit.vo;

import com.beneway.basic.common.system.entity.sys_user_unit.SysUserUnit;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TreeUnit {

    private Integer id;

    private String name;

    private String peopleIds;

    private List<TreeUnit> children;

    public String listToString(List<SysUserUnit> data, String joinString){
        StringBuilder stringBuilder = new StringBuilder();
        for (SysUserUnit t : data) {
            stringBuilder.append(t.getSysUserId()).append(joinString);
        }
        return stringBuilder.toString();
    }
}
