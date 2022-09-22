package com.beneway.basic.system.sys_unit.entity.vo;


import com.beneway.basic.system.sys_user_unit.entity.po.SysUserUnit;
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
