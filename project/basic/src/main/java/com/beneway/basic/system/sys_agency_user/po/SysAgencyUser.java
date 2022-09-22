package com.beneway.basic.system.sys_agency_user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 *
 * @author LiChen
 * @email dcam00r0@qq.com
 * @date 2022-05-30 18:27:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_agency_user")
public class SysAgencyUser implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
	/**
	 * 部门id
	 */
		private String agencyId;
	/**
	 * 用户id
	 */
		private String userId;

}
