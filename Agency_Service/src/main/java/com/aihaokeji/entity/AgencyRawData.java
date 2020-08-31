package com.aihaokeji.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class AgencyRawData {
    private String SCode;   //:"300123"
    private String  SName;  //:"亚光科技"
    @JSONField(format="yyyy-MM-dd")
    private LocalDate TDate;    //:"2020-07-17"
    private BigDecimal CPrice;  //:"16.94"
    private BigDecimal Chgradio;    //:"10"
    private String Ntransac;    //:"240775355"
    private BigDecimal TurNover;    //:"3818181999"
    private BigDecimal Statistic;   //:"21.48"
    private BigDecimal Dchratio;    //:""
    private String RID; //:"3726670";
    private String CTypeDes;    //:"连续三个交易日内，涨幅偏离值累计达到20%的证券"
    private String MEMO;    //:""
    private BigDecimal  BMoney; ///:"503971709.55"
    private BigDecimal SMoney;  //:"22607680.2"
    private BigDecimal PBuy;    //:"481364029.35"
    private BigDecimal PBRate;  //:"12.61"
    private int BSL;  //:"3"
    private int  SSL; //:"1"
    private BigDecimal TurnRate;    //:"12.479"
    private BigDecimal AGSZBHXS;    //:"9794867320.7"
    private String RChange1DC;  //:""
    private String RChange1DO;  //:""
    private String RChange2DC;  //:""
    private String RChange2DO;  //:""
    private String RChange3DC;  //:""
    private String RChange3DO;  //:""
    private String RChange5DC;  //:""
    private String RChange5DO;  //:""
    private String  RChange10DC;    //:""
    private String RChange10DO; //:""
    private String RChange15DC; //:""
    private String RChange15DO; //:""
    private String RChange20DC; //:""
    private String RChange20DO; //:""
    private String RChange30DC; //:""
    private String RChange30DO; //:""
    private BigDecimal RChange1M;   //:"53.32429991"
    private BigDecimal RChange3M;   //:"53.32429991"
    private BigDecimal RChange6M;   //:"105.23579202"
    private BigDecimal RChange1Y;   //:"89.00890869"

}
