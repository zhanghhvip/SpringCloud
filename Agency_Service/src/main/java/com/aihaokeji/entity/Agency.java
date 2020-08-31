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
 * 机构买卖表
 * </p>
 *
 * @author zhh
 * @since 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_agency")
public class Agency implements Serializable {

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
     * 机构净买额（元）
     */
    private BigDecimal agencyNetBuy;

    /**
     * 机构买入额（元）
     */
    private BigDecimal agencyBuy;

    /**
     * 机构卖出额（元）
     */
    private BigDecimal agencySell;

    /**
     * 市场总成交额（元）
     */
    private BigDecimal marketDeal;

    /**
     * 机构净买额占市场总成交比
     */
    private BigDecimal agencyNetBuyRatio;

    /**
     * 买方机构数
     */
    private Integer buyAgencyNum;

    /**
     * 卖方机构数
     */
    private Integer sellAgencyNum;

    /**
     * 上榜理由
     */
    private String reason;

    /**
     * 日期
     */
    private LocalDate tradeDate;


}
