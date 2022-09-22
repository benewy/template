package com.beneway.basic.log.log_sql.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beneway.basic.log.log_sql.enums.LogSqlTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * sql日志表
 *
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022-04-21 09:43:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("log_sql")
public class LogSql implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 表名
     */
    private String tableName;
    /**
     * sql类型
     */
    private LogSqlTypeEnum sqlType;
    /**
     * 执行的sql
     */
    private String exeSql;
    /**
     * 源数据
     */
    private String sourceData;
    /**
     * 字段
     */
    private String fields;
    /**
     * 查询
     */
    private String whereSql;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
