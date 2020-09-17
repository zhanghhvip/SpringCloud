package com.aihaokeji.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 大宗交易表
 * </p>
 *
 * @author zhh
 * @since 2020-09-16
 */
@TableName("table_dzjy")
public class Dzjy extends Model<Dzjy> {

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getChangeRatio() {
        return changeRatio;
    }

    public void setChangeRatio(BigDecimal changeRatio) {
        this.changeRatio = changeRatio;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getDeal() {
        return deal;
    }

    public void setDeal(BigDecimal deal) {
        this.deal = deal;
    }

    public BigDecimal getZyRatio() {
        return zyRatio;
    }

    public void setZyRatio(BigDecimal zyRatio) {
        this.zyRatio = zyRatio;
    }

    public BigDecimal getDealLtszRatio() {
        return dealLtszRatio;
    }

    public void setDealLtszRatio(BigDecimal dealLtszRatio) {
        this.dealLtszRatio = dealLtszRatio;
    }

    public String getBuyAgencyName() {
        return buyAgencyName;
    }

    public void setBuyAgencyName(String buyAgencyName) {
        this.buyAgencyName = buyAgencyName;
    }

    public String getSellAgencyName() {
        return sellAgencyName;
    }

    public void setSellAgencyName(String sellAgencyName) {
        this.sellAgencyName = sellAgencyName;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Dzjy{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", changeRatio=" + changeRatio +
        ", closePrice=" + closePrice +
        ", price=" + price +
        ", volume=" + volume +
        ", deal=" + deal +
        ", zyRatio=" + zyRatio +
        ", dealLtszRatio=" + dealLtszRatio +
        ", buyAgencyName=" + buyAgencyName +
        ", sellAgencyName=" + sellAgencyName +
        ", tradeDate=" + tradeDate +
        "}";
    }
}
