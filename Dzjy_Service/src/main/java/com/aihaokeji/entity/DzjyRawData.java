package com.aihaokeji.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@Data
public class DzjyRawData implements Serializable {
    //"TDATE":"2020-09-22T00:00:00","SECUCODE":"300001","SNAME":"特锐德","PRICE":21.5,"TVOL":10.38,
    // "TVAL":223.11,"BUYERCODE":"80032251","BUYERNAME":"国信证券深圳红岭中路证券营业部",
    // "SALESCODE":"80467570","SALESNAME":"中国银河证券深圳水贝证券营业部","
    // Stype":"EQA","Unit":"万股","RCHANGE":-2.4705,"CPRICE":18.16,"YSSLTAG":944723457.0,"
    // Zyl":0.183920704845815,"Cjeltszb":0.000130046447566315,"RCHANGE1DC":"-","RCHANGE5DC":"-","
    // RCHANGE10DC":"-","RCHANGE20DC":"-","TEXCH":"CNSESZ"},
    //2020-07-29T00:00:00
    @JSONField(format="yyyy-MM-ddTHH:mm:ss")
    private Date TDATE;
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


}
