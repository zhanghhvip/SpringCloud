package com.aihaokeji.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 大宗交易表
 * </p>
 *
 * @author zhh
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_dzjy")
public class Dzjy implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 股票名称
     */
    private String name;

    /**
     * 涨跌幅
     */
    private BigDecimal changeRatio;

    /**
     * 收盘价
     */
    private BigDecimal closePrice;

    /**
     * 成交价
     */
    private BigDecimal price;

    /**
     * 成交量(万股)
     */
    private BigDecimal volume;

    /**
     * 成交额(万元)
     */
    private BigDecimal deal;

    /**
     * 折溢率
     */
    private BigDecimal zyRatio;

    /**
     * 成交额占流通市值比
     */
    private BigDecimal dealLtszRatio;

    /**
     * 买方机构
     */
    private String buyAgencyName;

    /**
     * 卖方机构
     */
    private String sellAgencyName;

    /**
     * 日期
     */
    private LocalDate tradeDate;


}
