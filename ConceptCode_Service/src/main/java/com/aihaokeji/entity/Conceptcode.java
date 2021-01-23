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
 * 概念代码表
 * </p>
 *
 * @author zhh
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_conceptcode")
public class Conceptcode implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
      @JSONField(serialize = false)
    private Long id;

    /**
     * 概念代码
     */
    @JSONField(name = "f12")
    private String conceptCode;

    /**
     * 概念名称
     */
    @JSONField(name = "f14")
    private String conceptName;


}
