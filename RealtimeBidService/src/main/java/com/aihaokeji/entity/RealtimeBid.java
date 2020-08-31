package com.aihaokeji.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 集合竞价表
 * </p>
 *
 * @author zhh
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_realtime_bid")
public class RealtimeBid implements Serializable {

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
     * 今日开盘价
     */
    private BigDecimal todayOpenPrice;

    /**
     * 昨日收盘价
     */
    private BigDecimal yesterdayClosePrice;

    /**
     * 当前价格
     */
    private BigDecimal curPrice;

    /**
     * 今日最高价
     */
    private BigDecimal todayHigh;

    /**
     * 今日最低价
     */
    private BigDecimal todayLow;

    /**
     * 竞买价(买一价)
     */
    private BigDecimal buyPrice;

    /**
     * 竞卖价(卖一价)
     */
    private BigDecimal sellPrice;

    /**
     * 成交量(股)
     */
    private Long volume;

    /**
     * 成交额(元)
     */
    private BigDecimal deal;

    /**
     * 买一量(股)
     */
    private Long buyOneVolume;

    /**
     * 买一价(元)
     */
    private BigDecimal buyOnePrice;

    /**
     * 买二量(股)
     */
    private Long buyTwoVolume;

    /**
     * 买二价(元)
     */
    private BigDecimal buyTwoPrice;

    /**
     * 买三量(股)
     */
    private Long buyThreeVolume;

    /**
     * 买三价(元)
     */
    private BigDecimal buyThreePrice;

    /**
     * 买四量(股)
     */
    private Long buyFourVolume;

    /**
     * 买四价(元)
     */
    private BigDecimal buyFourPrice;

    /**
     * 买五量(股)
     */
    private Long buyFiveVolume;

    /**
     * 买五价(元)
     */
    private BigDecimal buyFivePrice;

    /**
     * 卖一量(股)
     */
    private Long sellOneVolume;

    /**
     * 卖一价(元)
     */
    private BigDecimal sellOnePrice;

    /**
     * 卖二量(股)
     */
    private Long sellTwoVolume;

    /**
     * 卖二价(元)
     */
    private BigDecimal sellTwoPrice;

    /**
     * 卖三量(股)
     */
    private Long sellThreeVolume;

    /**
     * 卖三价(元)
     */
    private BigDecimal sellThreePrice;

    /**
     * 卖四量(股)
     */
    private Long sellFourVolume;

    /**
     * 卖四价(元)
     */
    private BigDecimal sellFourPrice;

    /**
     * 卖五量(股)
     */
    private Long sellFiveVolume;

    /**
     * 卖五价(元)
     */
    private BigDecimal sellFivePrice;

    /**
     * 日期
     */
    private LocalDate tradeDate;

    /**
     * 时间
     */
    private LocalTime tradeTime;


}
