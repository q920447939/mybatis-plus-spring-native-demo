package com.withmes.base;

import com.google.common.base.Throwables;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.Locale;


@Component
@Slf4j
public class ExceptionFilter {

    private final MessageSource messageSource;

    public ExceptionFilter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Result convert(Exception exception, HttpServletRequest request) {
        if (request == null) {
            return this.convert(exception);
        }

        Locale locale = RequestContextUtils.getLocale(request);

        if (isRestRequest(request)) {
            Result res = this.convert(exception);

            // 多语言提示
            res.setMessage(messageSource.getMessage(res.getCode(), null, locale));
            return res;
        } else {
            return new Result();
        }
    }

    public Result convert(Exception exception) {
        Throwable thr = Throwables.getRootCause(exception);
        Result res;
        if (thr instanceof IllegalArgumentException iae) {
            // 非法参数异常处理
            res = ExceptionConverter.handleIllegalArgumentException(iae);
        } else if (thr instanceof ConnectException ce) {
            // 网络连接异常处理
            res = ExceptionConverter.handleConnectException(ce);
        } else if (thr instanceof IOException ioe) {
            // IO异常处理
            res = ExceptionConverter.handleIoException(ioe);
        } else if (thr instanceof SQLException se) {
            // SQL异常处理
            res = ExceptionConverter.handleSqlException(se);
        } else {
            // 其他异常
            res = ExceptionConverter.handleOtherException(thr);
        }

        // 输出简化后的错误堆栈
        StringBuilder traceBuilder = new StringBuilder();
        res.getTrace().forEach(e -> traceBuilder.append(e).append(System.lineSeparator()).append("\t"));
        log.error(exception.getMessage() + "\n{}", traceBuilder);

        return res;
    }

    private boolean isRestRequest(HttpServletRequest request) {
        String xr = request.getHeader("X-Requested-With");
        String xmlHttpRequest = "XMLHttpRequest";
        if (xmlHttpRequest.equals(xr)) {
            return true;
        }
        if (request.getRequestURI().contains(Constant.API_NORMAL)) {
            return true;
        }
        String contentType = request.getContentType();
        return contentType != null && contentType.toLowerCase().startsWith("multipart/");
    }
}
