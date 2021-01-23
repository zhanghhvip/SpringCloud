package com.aihaokeji.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行业代码表
 * </p>
 *
 * @author zhh
 * @since 2020-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_industrycode")
public class Industrycode implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
      @JSONField(serialize = false)
    private Long id;

    /**
     * 行业代码
     */
    @JSONField(name = "f12")
    private String industryCode;

    /**
     * 行业名称
     */
    @JSONField(name = "f14")
    private String industryName;


}
