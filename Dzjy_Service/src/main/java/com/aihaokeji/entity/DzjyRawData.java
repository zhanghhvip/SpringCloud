package com.aihaokeji.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class DzjyRawData implements Serializable {
    //2020-07-29T00:00:00
    @JSONField(format="yyyy-MM-ddTHH:mm:ss")
    private LocalDate TDATE;
    private String SECUCODE;
    private String SNAME;
    private BigDecimal PRICE;
    private BigDecimal TVOL;
    private BigDecimal TVAL;
    private String BUYERCODE;
    private String BUYERNAME;
    private String SALESCODE;
    private String SALESNAME;
    private String  Stype;
    private String Unit;
    private BigDecimal RCHANGE;
    private BigDecimal CPRICE;
    private  BigDecimal YSSLTAG;
    private BigDecimal  Zyl;
    private BigDecimal   Cjeltszb;
    private  String RCHANGE1DC;
    private String   RCHANGE5DC;
    private String  RCHANGE10DC;
    private String   RCHANGE20DC;
    private String TEXCH;

    public LocalDate getTDATE() {
        return TDATE;
    }

    public void setTDATE(LocalDate TDATE) {
        this.TDATE = TDATE;
    }

    public String getSECUCODE() {
        return SECUCODE;
    }

    public void setSECUCODE(String SECUCODE) {
        this.SECUCODE = SECUCODE;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public BigDecimal getPRICE() {
        return PRICE;
    }

    public void setPRICE(BigDecimal PRICE) {
        this.PRICE = PRICE;
    }

    public BigDecimal getTVOL() {
        return TVOL;
    }

    public void setTVOL(BigDecimal TVOL) {
        this.TVOL = TVOL;
    }

    public BigDecimal getTVAL() {
        return TVAL;
    }

    public void setTVAL(BigDecimal TVAL) {
        this.TVAL = TVAL;
    }

    public String getBUYERCODE() {
        return BUYERCODE;
    }

    public void setBUYERCODE(String BUYERCODE) {
        this.BUYERCODE = BUYERCODE;
    }

    public String getBUYERNAME() {
        return BUYERNAME;
    }

    public void setBUYERNAME(String BUYERNAME) {
        this.BUYERNAME = BUYERNAME;
    }

    public String getSALESCODE() {
        return SALESCODE;
    }

    public void setSALESCODE(String SALESCODE) {
        this.SALESCODE = SALESCODE;
    }

    public String getSALESNAME() {
        return SALESNAME;
    }

    public void setSALESNAME(String SALESNAME) {
        this.SALESNAME = SALESNAME;
    }

    public String getStype() {
        return Stype;
    }

    public void setStype(String stype) {
        Stype = stype;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public BigDecimal getRCHANGE() {
        return RCHANGE;
    }

    public void setRCHANGE(BigDecimal RCHANGE) {
        this.RCHANGE = RCHANGE;
    }

    public BigDecimal getCPRICE() {
        return CPRICE;
    }

    public void setCPRICE(BigDecimal CPRICE) {
        this.CPRICE = CPRICE;
    }

    public BigDecimal getYSSLTAG() {
        return YSSLTAG;
    }

    public void setYSSLTAG(BigDecimal YSSLTAG) {
        this.YSSLTAG = YSSLTAG;
    }

    public BigDecimal getZyl() {
        return Zyl;
    }

    public void setZyl(BigDecimal zyl) {
        Zyl = zyl;
    }

    public BigDecimal getCjeltszb() {
        return Cjeltszb;
    }

    public void setCjeltszb(BigDecimal cjeltszb) {
        Cjeltszb = cjeltszb;
    }

    public String getRCHANGE1DC() {
        return RCHANGE1DC;
    }

    public void setRCHANGE1DC(String RCHANGE1DC) {
        this.RCHANGE1DC = RCHANGE1DC;
    }

    public String getRCHANGE5DC() {
        return RCHANGE5DC;
    }

    public void setRCHANGE5DC(String RCHANGE5DC) {
        this.RCHANGE5DC = RCHANGE5DC;
    }

    public String getRCHANGE10DC() {
        return RCHANGE10DC;
    }

    public void setRCHANGE10DC(String RCHANGE10DC) {
        this.RCHANGE10DC = RCHANGE10DC;
    }

    public String getRCHANGE20DC() {
        return RCHANGE20DC;
    }

    public void setRCHANGE20DC(String RCHANGE20DC) {
        this.RCHANGE20DC = RCHANGE20DC;
    }

    public String getTEXCH() {
        return TEXCH;
    }

    public void setTEXCH(String TEXCH) {
        this.TEXCH = TEXCH;
    }
}
