package com.aihaokeji.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
//import java.sql.Date;

import java.time.LocalDate;

@Data
public class LhbRawData {
    private String SCode;   //代码:"300841";
    private String SName;   //:名称"康华生物"
    private BigDecimal ClosePrice; //收盘价:"557.76"
    private BigDecimal Chgradio;//涨跌幅:"-9.9995"
    private BigDecimal Dchratio;//换手率:"35.693"
    private BigDecimal JmMoney;//:龙虎榜净买入额（万）"532951366.05"
    private BigDecimal  Turnover;//龙虎榜总成交额（万）:3175652562"
    private String Ntransac;//:"5354013"
    private String Ctypedes;//:上榜原因"日振幅值达到15%的前五只证券"
    private String Oldid;//:"3725125"
    private BigDecimal  Smoney;//龙虎榜卖出额:"276898358"
    private BigDecimal  Bmoney;//龙虎榜买入额:"809849724.05"
    private BigDecimal  ZeMoney;//龙虎榜成交额:"1086748082.05"
    @JSONField(format="yyyy-MM-dd")
    private LocalDate Tdate;//日期:"2020-07-16"
    private BigDecimal  JmRate; //净买额占总成交比:"16.78"
    private BigDecimal  ZeRate; //成交额占总成交比:"34.22"
    private BigDecimal  Ltsz; //流通市值:"8366400000"
    private String Rchange1dc;//:""
    private String Rchange1do;//:""
    private String Rchange2dc;//:""
    private String Rchange2do;//:""
    private String Rchange3dc;//:""
    private String Rchange3do;//:""
    private String Rchange5dc;//:""
    private String Rchange5do;//:""
    private String Rchange10dc;//:""
    private String Rchange10do;//:""
    private String Rchange15dc;//:""
    private String Rchange15do;//:""
    private String Rchange20dc;//:""
    private String Rchange20do;//:""
    private String Rchange30dc;//:""
    private String Rchange30do;//:""
    private BigDecimal  Rchange1m;//:"692.61048742"
    private BigDecimal  Rchange3m;//:"692.61048742"
    private BigDecimal  Rchange6m;//:"692.61048742"
    private BigDecimal  Rchange1y;//:"692.61048742"
    private String  SumCount;//:""
    private String JGBSumCount;//:""
    private String JGSSumCount;//:""
    private String JGBMoney;//:""
    private String JGSMoney;//:""
    private String JGJMMoney;//:""
    private String JD;//解读:"实力游资卖出，成功率52.82%"
    private String DP;//:"实力游资卖出，成功率52.82%"

}
