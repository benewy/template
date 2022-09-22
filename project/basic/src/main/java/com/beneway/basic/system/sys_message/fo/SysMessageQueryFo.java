package com.beneway.basic.system.sys_message.fo;

import com.beneway.basic.enums.UserTypeEnum;
import com.beneway.basic.utils.page.PageQuery;
import lombok.Data;

@Data
public class SysMessageQueryFo extends PageQuery {
    /**
     * lookUser
     */
    private String lookUser;
    /**
     * 用户类型
     */
    private UserTypeEnum userType;

    public String getUserType() {
        return userType != null ? userType.getType() : null;
    }

}
