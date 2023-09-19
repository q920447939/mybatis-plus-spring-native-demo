package com.withmes.base;

import lombok.Data;


/**
 * 分页参数
 *
 * @author CGM
 */
@Data
public class PageParam {
    private int page;

    private int limit;

    private String sort;
}
