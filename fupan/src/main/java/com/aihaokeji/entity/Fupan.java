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
 * 复盘表
 * </p>
 *
 * @author zhh
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_fupan")
public class Fupan implements Serializable {

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
     * 首次涨停时间
     */
    private LocalTime firstTime;

    /**
     * 最终涨停时间
     */
    private LocalTime lastTime;

    /**
     * 连续涨停天数
     */
    private Integer duration;

    /**
     * 涨停原因类别
     */
    private String reason;

    /**
     * 涨停封单量(万)
     */
    private BigDecimal fdl;

    /**
     * 涨停封单额(万)
     */
    private BigDecimal fde;

    /**
     * 流通市值(亿)
     */
    private BigDecimal ltsz;

    /**
     * 涨停封成比
     */
    private BigDecimal fdlVolRatio;

    /**
     * 涨停封流比
     */
    private BigDecimal fdlLtgRatio;

    /**
     * 涨停开板次数
     */
    private Integer kbNum;

    /**
     * 上市天数
     */
    private Integer ssDays;

    /**
     * 涨停类型
     */
    private String type;

    /**
     * 几天几板
     */
    private String ztDays;

    /**
     * 日期
     */
    private LocalDate tradeDate;


}
