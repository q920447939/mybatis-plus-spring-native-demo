package com.withmes.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 通用返回对象
 *
 * @author CGM
 */
@Data
@NoArgsConstructor
@Slf4j
public class Result {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String exceptionMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    private String detail;

    private boolean success = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer total;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> trace;

    public Result(Object data) {
        this.data = data;
        if (data == null) {
            this.setTotal(0);
            return;
        }
        try {
            if (data instanceof List) {
                // 返回列表
                setTotal(((List<?>) data).size());
            } else if (data instanceof IPage<?> page) {
                // 返回分页结果
                this.data = page.getRecords();
                setTotal((int) page.getTotal());
            } else {
                // 其他, 直接返回
                setTotal(1);
            }
        } catch (Exception e) {
            log.error(Constant.LOG_ERROR_TITLE, e);
            throw new IllegalArgumentException(ErrorCode.SYS_PAGE_FAILED);
        }
    }

    public Result(String code, String message, String detail, boolean success, List<String> trace) {
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.success = success;
        this.trace = trace;
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result failed(String code, String detail, List<String> trace) {
        return new Result(code, null, detail, false, trace);
    }

    public static Result failed(String code, String message) {
        return new Result(code, message, null, false ,null);
    }
}
