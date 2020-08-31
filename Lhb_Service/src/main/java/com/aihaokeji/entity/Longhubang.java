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
 * 龙虎榜表
 * </p>
 *
 * @author zhh
 * @since 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_longhubang")
public class Longhubang implements Serializable {

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
     * 收盘价
     */
    private BigDecimal closePrice;

    /**
     * 涨跌幅
     */
    private BigDecimal changeRatio;

    /**
     * 换手率
     */
    private BigDecimal turnoverRatio;

    /**
     * 流通市值（元）
     */
    private BigDecimal ltsz;

    /**
     * 龙虎榜净买额（元）
     */
    private BigDecimal lhbNetBuy;

    /**
     * 龙虎榜买入额（元）
     */
    private BigDecimal lhbBuy;

    /**
     * 龙虎榜卖出额（元）
     */
    private BigDecimal lhbSell;

    /**
     * 龙虎榜成交额（元）
     */
    private BigDecimal lhbDeal;

    /**
     * 市场总成交额（元）
     */
    private BigDecimal marketDeal;

    /**
     * 净买额占市场总成交比
     */
    private BigDecimal netBuyRatio;

    /**
     * 成交额占市场总成交比
     */
    private BigDecimal lhbDealRatio;

    /**
     * 上榜理由
     */
    private String reason;

    /**
     * 解读
     */
    private String jd;

    /**
     * 日期
     */
    private LocalDate tradeDate;


}
