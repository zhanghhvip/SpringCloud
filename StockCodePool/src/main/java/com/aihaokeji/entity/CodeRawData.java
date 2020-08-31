package com.aihaokeji.entity;

import lombok.Data;

@Data
public class CodeRawData {
    /**
     * 股票代码
     */
    private String f12;

    /**
     * 股票名称
     */
    private String f14;

    /**
     * 所属板块
     */
    private String f100;
}
